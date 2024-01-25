package com.walklown.attempt.server;

import org.springframework.stereotype.Component;

@Component
public class Child1 extends Parent {

    @Override
    public void sayYes() {
        System.out.println("Child1: yes!");
    }
}
