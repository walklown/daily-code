package com.walklown.attempt.server;

import org.springframework.stereotype.Component;

@Component
public class Child2 extends Parent {

    @Override
    public void run() {
        System.out.println("Child2: run!");
    }
}
