package com.walklown.attempt.dubbo.provider.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class DemoServiceImplMock implements DemoService {
    @Override
    public String sayHello(String name) {
        return "mock";
    }

    @Override
    public String sayHelloAsync(String name) {
        return "mock";
    }

    @Override
    public Future<String> sayHelloAsync1(String name) {
        return CompletableFuture.completedFuture("mock");
    }

    @Override
    public String sayHelloThrEx(String name) {
        return "mock";
    }
}
