package com.walklown.attempt.server.dubbo.provider.impl;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<String> sayHelloAsync1(String name) {
        return CompletableFuture.completedFuture("mock");
    }

    @Override
    public String sayHelloThrEx(String name) {
        return "mock";
    }
}
