package com.zzp.learn.walklown.redisson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zzp.learn.walklown.json.JacksonUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class JedisDelayingQueue<T> {

    static class TaskItem<T> {
        public String id;
        public T msg;
    }
    // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference

    private TypeReference<TaskItem<T>> taskType = new TypeReference<>() {
    };
    private static Jedis jedis;
    private String queueKey;

    static {

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        JedisPool pool = new JedisPool(jedisPoolConfig,
                "192.168.8.4",
                6379, 3000);
        jedis = pool.getResource();
    }

    public JedisDelayingQueue(String queueKey) {
        this.queueKey = queueKey;
    }

    public void delay(T msg) {
        TaskItem<T> task = new TaskItem();
        task.id = UUID.randomUUID().toString();  // 分配唯一的 uuid
        task.msg = msg;
        String s = JacksonUtils.toJSONString(task);  // fastjson 序列化
        jedis.zadd(queueKey, System.currentTimeMillis() + 5000, s);
//        RBlockingQueue<String> blockingFairQueue = redissonClient.getBlockingQueue(queueKey);
//        RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);  // 塞入延时队列 ,5s 后再试
//        delayedQueue.offer(s, 5, TimeUnit.SECONDS);
        System.out.println(LocalDateTime.now().toString() + ":" + s);
    }

    public void loop() {
//        String s = null;
        while (!Thread.interrupted()) {
//            RBlockingQueue<String> blockingFairQueue = redissonClient.getBlockingQueue(queueKey);
//            RDelayedQueue<String> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
//            try {
//                s = blockingFairQueue.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (StringUtil.isEmpty(s)) {
//                try {
//                    Thread.sleep(500);  // 歇会继续
//                } catch (InterruptedException e) {
//                    break;
//                }
//                continue;
//            }
            // 只取一条
            Set<String> values = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis(), 0, 1);
            if (values.isEmpty()) {
                try {
                    Thread.sleep(500);
                    // 歇会继续
                } catch (InterruptedException e) {
                    break;
                }
                continue;
            }
            String s = values.iterator().next();
            if (jedis.zrem(queueKey, s) > 0) {  // 抢到了
                TaskItem<T> task = null;
                try {
                    task = JacksonUtils.OBJ_MAPPER.readValue(s, taskType);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                // fastjson 反序列化
                this.handleMsg(task.msg);
            }
//            TaskItem<T> task = JSON.parseObject(s, TaskType);  // fastjson 反序列化
//            this.handleMsg(task.msg);
        }
    }

    public void handleMsg(T msg) {
        System.out.println(LocalDateTime.now().toString() + ":" + msg);
    }

    public static void main(String[] args) {
//        Jedis jedis = new Jedis();
        JedisDelayingQueue queue = new JedisDelayingQueue<>("q-demo");
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