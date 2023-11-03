package com.walklown.learn.jarkata.base.jdk21;


import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.IntStream;

class LoomTest {

    public static void main(String[] args) throws Exception {
        var observed = new ConcurrentSkipListSet<String>();

        var threads = IntStream
                .range(0, 100)
                .mapToObj(index -> Thread.ofVirtual()
                        .unstarted(() -> {
                            var first = index == 0;
                            if (first) {
                                observed.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) {
                                observed.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) {
                                observed.add(Thread.currentThread().toString());
                            }
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            if (first) {
                                observed.add(Thread.currentThread().toString());
                            }
                        }))
                .toList();

        for (var t : threads) {
            t.start();
        }

        for (var t : threads) {
            t.join();
        }

        for (String s : observed) {
            System.out.println(s);
        }

    }

}