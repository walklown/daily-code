package com.zzp.sync;

import java.util.concurrent.CountDownLatch;
import static java.lang.System.out;

public class CountTest {
    private static int r=0;
    public static void main(String[] args) {
        CountDownLatch count = new CountDownLatch(10);
        for (int i=0;i<10;i++){
            new Thread(() -> {
                try {
                    r++;
                    Thread.sleep(r*1000);
                    out.println(r);
//                    count.await();
                    count.countDown();
                    r++;
                    out.println("e"+r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
//        for (int i=0;i<10;i++){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            count.countDown();
//        }
        try {
            count.await();
            out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
