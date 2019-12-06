package com.gupaoedu.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class App {
    public static void main(String[] args) throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    ZkLock zkLock = new ZkLock();
                    zkLock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Thread-" + i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }
}
