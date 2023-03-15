package com.walklown.learn.jarkata.juc;

import java.time.LocalDateTime;

public class SyncDemo {

    private static SyncDemo syncDemo = new SyncDemo();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(target);
        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-class wait");
//        SyncDemo.class.notify();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        synchronized (syncDemo) {
            System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-syncDemo wait");
            syncDemo.notify();
        }
        thread1.join();
    }

    private synchronized void test() throws InterruptedException {
        System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-wait");
        this.wait();
        System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-notify");
    }

    static final Runnable target = () -> {
        try {
            syncDemo.test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
}
