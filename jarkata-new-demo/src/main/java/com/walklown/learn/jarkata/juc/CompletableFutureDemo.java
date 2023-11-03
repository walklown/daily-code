package com.walklown.learn.jarkata.juc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {

//    public static void main(String[] args) {
//
//        List<CompletableFuture<Void>> tasks = new ArrayList<>(128);
//        for (int i = 0; i < 10; i++) {
//            String text = "i:" + i;
//            CompletableFuture<Void> task = CompletableFuture
//                    .runAsync(() -> {
//                        System.out.println(text);
//                        try {
//                            Thread.sleep(1001);
//                        } catch (InterruptedException e) {
//                            throw new RuntimeException(e);
//                        }
//                        System.out.println(text);
//                    })
//                    .orTimeout(1, TimeUnit.SECONDS);
//            tasks.add(task);
//        }
//        CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();
//    }

    public static void main(String[] args) {
        new Thread(()->{
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        System.out.println("exit");
    }
}
