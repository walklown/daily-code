package com.zzp.learn.walklown.jarkata;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ZzpDemo {

    private static final AtomicInteger count = new AtomicInteger();

    private static final Semaphore semaphore1 = new Semaphore(1);

    private static final Semaphore semaphore2 = new Semaphore(0);

    private static final Semaphore semaphore3 = new Semaphore(0);

    private static final Semaphore semaphore4 = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(()->{
            for (;;) {
                try {
                    semaphore1.acquire();
                    if (count.incrementAndGet() > 102) {
                        semaphore2.release();
                        break;
                    }
                    System.out.println("菜");
                    semaphore2.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread b = new Thread(()->{
            for (;;) {
                try {
                    semaphore2.acquire();
                    if (count.incrementAndGet() > 102) {
                        semaphore3.release();
                        break;
                    }
                    System.out.println("鸟");
                    semaphore3.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread c = new Thread(()->{
            for (;;) {
                try {
                    semaphore3.acquire();
                    if (count.incrementAndGet() > 102) {
                        semaphore4.release();
                        break;
                    }
                    System.out.println("网");
                    semaphore4.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread d = new Thread(()->{
            for (;;) {
                try {
                    semaphore4.acquire();
                    if (count.incrementAndGet() > 102) {
                        semaphore1.release();
                        break;
                    }
                    System.out.println("络");
                    semaphore1.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
