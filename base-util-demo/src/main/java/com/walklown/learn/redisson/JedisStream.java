package com.walklown.learn.redisson;

import com.fasterxml.jackson.core.type.TypeReference;
import redis.clients.jedis.*;
import redis.clients.jedis.resps.StreamEntry;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JedisStream<T> {

    static class TaskItem<T> {
        public String id;
        public T msg;
    }
    // fastjson 序列化对象中存在 generic 类型时，需要使用 TypeReference

    private TypeReference<JedisDelayingQueue.TaskItem<T>> taskType = new TypeReference<JedisDelayingQueue.TaskItem<T>>() {
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

    public static void main(String[] args) {
        Map<String, String> task = new HashMap<>();
        task.put("id", UUID.randomUUID().toString());
        StreamEntryID streamEntryID = new StreamEntryID(
                LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli(), 0);
        task.put("msg", streamEntryID.toString());
        jedis.xadd("testStream", streamEntryID, task);
        Long len = jedis.xlen("testStream");
        System.out.println(len) ;

        Map<String, StreamEntryID> map = new HashMap<>();
        map.put("testStream", null);
//        for (Map.Entry<String, StreamEntryID> entry : map.entrySet()) {
//            List<Map.Entry<String, List<StreamEntry>>> list = jedis.xread(1, 0, entry);
//            System.out.println(list);
//        }
        jedis.xdel("testStream", streamEntryID);
    }
}
