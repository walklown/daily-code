package com.zzp.learn.walklown.jarkata;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ZzpDemo1 {

    private static final AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(()->{
            int num;
            while ((num = count.get()) < 102) {
                if (num % 4 == 0) {
                    System.out.println("菜");
                    count.incrementAndGet();
                }
            }
        });
        Thread b = new Thread(()->{
            int num;
            while ((num = count.get()) < 102) {
                if (num % 4 == 1) {
                    System.out.println("鸟");
                    count.incrementAndGet();
                }
            }
        });
        Thread c = new Thread(()->{
            int num;
            while ((num = count.get()) < 102) {
                if (num % 4 == 2) {
                    System.out.println("网");
                    count.incrementAndGet();
                }
            }
        });
        Thread d = new Thread(()->{
            int num;
            while ((num = count.get()) < 102) {
                if (num % 4 == 3) {
                    System.out.println("络");
                    count.incrementAndGet();
                }
            }
        });
        a.start();
        b.start();
        c.start();
        d.start();
        a.join();
        b.join();
        c.join();
        d.join();
    }
}
