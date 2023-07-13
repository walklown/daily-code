package com.walklown.attempt.dubbo.provider;

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
    public String sayHelloThrEx(String name) {
        return "mock";
    }
}
