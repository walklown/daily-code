package com.walklown.learn.jarkata.juc;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static ReentrantLock lock = new ReentrantLock();

    private static AtomicInteger i = new AtomicInteger();

    private static Condition condition;

    public static void main(String[] args) throws InterruptedException {
        condition = lock.newCondition();
        Thread thread1 = new Thread(target);
        Thread thread2 = new Thread(target);
        Thread thread3 = new Thread(target1);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread3.start();
//        thread1.join();
//        thread2.join();
        thread3.join();

        lock.lock();
        try {
            condition.signal();
            System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-signal end");
        } finally {
            lock.unlock();
        }
    }

    static final Runnable target = () -> {
        lock.lock();
        try {
            System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-lock");
            System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-await");
            condition.await();
            System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-signal");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    };
    static final Runnable target1 = () -> {
        lock.lock();
        try {
            System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-wait");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-wait end");
            condition.signal();
            System.out.println(LocalDateTime.now().toString() + " - " + Thread.currentThread().getName() + "-signal end");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    };
}
