package com.zzp.learn.walklown.jarkata.juc;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterDemo {

    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    private static int rtCounter = 0;

    public static void main(String[] args) {
        System.out.println("starttime:" + LocalDateTime.now());
        while (true) {
            CompletableFuture.runAsync(() -> {
            }, executor).whenComplete((v, t) -> {
                if (rtCounter % 5000 == 0) {
                    System.out.println("5000endtime:" + LocalDateTime.now());
                }
                if (rtCounter > 20000) {
                    System.out.println("20000endtime:" + LocalDateTime.now());
                    rtCounter = 0;
                } else {
                    rtCounter++;
                }
            });
        }
    }
}
