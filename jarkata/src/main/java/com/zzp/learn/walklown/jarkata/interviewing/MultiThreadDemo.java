package com.zzp.learn.walklown.jarkata.interviewing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * //题目1
 * //共计15杯奶茶，有2个客户，A客户每次拿2杯，B客户每次拿3杯；
 * //如果剩余的奶茶不够客户每次拿的数量，则停止拿奶茶，请用java多线程模拟上面的描述。
 *
 * @author shoujing
 * @date 2019/12/10 22:39
 */
public class MultiThreadDemo {

    private static final CountDownLatch LATCH = new CountDownLatch(2);

    private static final AtomicInteger NUM_OF_MILK = new AtomicInteger(15);

    private static final int NUM_OF_A = 2;

    private static final int NUM_OF_B = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            for (; ; ) {
                int num = NUM_OF_MILK.get();
                if (num >= NUM_OF_A) {
                    if (NUM_OF_MILK.compareAndSet(num, num - NUM_OF_A)) {
                        System.out.println(String.format("A客户拿走两杯奶茶，剩余数量：%d", num - NUM_OF_A));
                    }
                } else {
                    break;
                }
            }
            LATCH.countDown();
        });
        Thread b = new Thread(() -> {
            for (; ; ) {
                int num = NUM_OF_MILK.get();
                if (num >= NUM_OF_B) {
                    if (NUM_OF_MILK.compareAndSet(num, num - NUM_OF_B)) {
                        System.out.println(String.format("B客户拿走三杯奶茶，剩余数量：%d", num - NUM_OF_B));
                    }
                } else {
                    break;
                }
            }
            LATCH.countDown();
        });
        a.start();
        b.start();
        LATCH.await();
    }
}
