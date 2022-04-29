package com.zzp.learn.walklown.algorithm.archive220204;

public class Counter {

    public static long strangeCounter(long t) {
        t = t-1;
        long j = t/3;
        for (int i=0;Math.pow(2, i) < Math.pow(10, 12);i++) {
            long offset = (long)Math.pow(2, i) - 1;
            long num = (long)Math.pow(2, i + 1) - 1;
            if (num > j && offset <= j) {
                return num * 3 - t;
            } else if (offset < j) {
                continue;
            } else {
                throw new RuntimeException("超出");
            }
        }
        return -1;
    }
}
