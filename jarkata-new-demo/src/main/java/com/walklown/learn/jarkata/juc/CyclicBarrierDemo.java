package com.zzp.learn.walklown.jarkata.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 与CountDownLatch的主要区别，是可以reset
 *
 * @author 守愚（张智沛）
 * @date 2023-02-10
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("start"));

        List<CompletableFuture<Void>> tasks = new ArrayList<>(128);
        for (int i = 0; i < 3; i++) {
            String text = "i:" + i;
            CompletableFuture<Void> task = CompletableFuture
                    .runAsync(() -> {
                        System.out.println("into " + text);
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException | BrokenBarrierException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("execute " + text);
                    })
                    .orTimeout(1, TimeUnit.SECONDS);
            tasks.add(task);
        }
        CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();

        cyclicBarrier.reset();

        tasks = new ArrayList<>(128);
        for (int i = 0; i < 3; i++) {
            String text = "i:" + i;
            CompletableFuture<Void> task = CompletableFuture
                    .runAsync(() -> {
                        System.out.println("into " + text);
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException | BrokenBarrierException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("execute " + text);
                    })
                    .orTimeout(1, TimeUnit.SECONDS);
            tasks.add(task);
        }
//        CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();

    }
}
