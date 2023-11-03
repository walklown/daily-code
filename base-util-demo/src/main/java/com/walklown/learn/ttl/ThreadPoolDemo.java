package com.walklown.learn.ttl;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

//    public static void main(String[] args) throws InterruptedException {
////        TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>() {
////            protected String childValue(String parentValue) {
////                return initialValue();
////            }
////        };
//        TransmittableThreadLocal<String> transmittableThreadLocal = new TransmittableThreadLocal<>();
//        System.out.println(Thread.currentThread().getName() + " " + transmittableThreadLocal.get());
//        transmittableThreadLocal.set("a");
//        System.out.println(Thread.currentThread().getName() + " " + transmittableThreadLocal.get());
//        Thread t = new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + " " + transmittableThreadLocal.get());
//            transmittableThreadLocal.set("b");
//            System.out.println(Thread.currentThread().getName() + " " + transmittableThreadLocal.get());
//        });
//        t.start();
//        t.join();
//        System.out.println(Thread.currentThread().getName() + " " + transmittableThreadLocal.get());
////        transmittableThreadLocal.set("b");
////        System.out.println(Thread.currentThread().getName() + " " + transmittableThreadLocal.get());
//    }

    public static void main(String[] args) {
        for (int i = 1; i < 1; i++) {
            System.out.println("ss");
        }
    }
}
