//package com.zzp.learn.springboot;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.integration.redis.util.RedisLockRegistry;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RedisTests {
//
//    @Autowired
//    private RedisLockRegistry redisLockRegistry;
//
//    @Test
//    public void contextLoads() throws InterruptedException {
//        Lock lock = redisLockRegistry.obtain("lock");
//        boolean b1 = lock.tryLock(3, TimeUnit.SECONDS);
//        System.out.printf("b1 is : {}", b1);
//
//        TimeUnit.SECONDS.sleep(5);
//
//        boolean b2 = lock.tryLock(3, TimeUnit.SECONDS);
//        System.out.printf("b2 is : {}", b2);
//
//        lock.unlock();
//        lock.unlock();
//    }
//
//}
//
