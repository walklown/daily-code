package com.zzp.learn.walklown.algorithm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(()-> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
