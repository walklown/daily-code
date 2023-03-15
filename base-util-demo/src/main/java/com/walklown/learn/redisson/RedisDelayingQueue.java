package com.walklown.learn.redisson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.walklown.learn.json.JacksonUtils;
import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RedisDelayingQueue<T> {

    static class TaskItem<T> {
        public String id;
        public T msg;
    }  // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference

    private TypeReference<TaskItem<T>> TaskType = new TypeReference<TaskItem<T>>() {
    };
    private static RedissonClient redissonClient;
    private String queueKey;

    static {
        Config serverConfig = new Config();
        SingleServerConfig singleServerConfig = serverConfig.useSingleServer();
        singleServerConfig.setAddress("redis://192.168.8.4:6379");
//        singleServerConfig.setPassword();
        redissonClient = Redisson.create(serverConfig);
    }

    public RedisDelayingQueue(String queueKey) {
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem task = new TaskItem();
        task.id = UUID.randomUUID().toString();  // 分配唯一的 uuid
        task.msg = msg;
        String s = JacksonUtils.toJSONString(task);  // fastjson 序列化
        RBlockingQueue<String> blockingFairQueue = redissonClient.getBlockingQueue(queueKey);
//        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);  // 塞入延时队列 ,5s 后再试
        delayedQueue.offer(s, 5, TimeUnit.SECONDS);
        System.out.println(LocalDateTime.now().toString() + ":" + s);
    }

    public void loop() {
        String s = null;
        while (!Thread.interrupted()) {   // 只取一条
//            Set values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            RBlockingQueue<String> blockingFairQueue = redissonClient.getBlockingQueue(queueKey);
            RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
            try {
                s = blockingFairQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (StringUtil.isEmpty(s)) {
                try {
                    Thread.sleep(500);  // 歇会继续
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
//            String s = values.iterator().next();
//            if (jedis.zrem(queueKey, s) > 0) {  // 抢到了
            TaskItem<T> task = null;  // fastjson 反序列化
            try {
                task = JacksonUtils.OBJ_MAPPER.readValue(s, TaskType);
            } catch (JsonProcessingException e) {


            }
            this.handleMsg(task.msg);
//            }
        }
    }

    public void handleMsg(T msg) {
        System.out.println(LocalDateTime.now().toString() + ":" + msg);
    }

    public static void main(String[] args) {
//        Jedis jedis = new Jedis();
        RedisDelayingQueue queue = new RedisDelayingQueue<>("q-demo");
        Thread producer = new Thread(() -> {
            String base = "codehole";
            for (int i = 0; i < 10; i++) {
                queue.delay(base += i);
            }
        });
        Thread consumer = new Thread(() -> queue.loop());
        producer.start();
        consumer.start();
        try {
            producer.join();
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
        }
    }
}