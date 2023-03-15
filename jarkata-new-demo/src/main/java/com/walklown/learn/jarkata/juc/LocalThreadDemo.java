package com.walklown.learn.jarkata.juc;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class LocalThreadDemo {
    public static void main(String[] args) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);//一分钟
        threadPoolTaskExecutor.shutdown();
    }
}
