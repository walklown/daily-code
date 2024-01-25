package com.walklown.attempt.server;

import org.springframework.stereotype.Component;

@Component
public class Parent {

    public void sayHello() {
        System.out.println("Root: hello!");
    }

    public void sayYes() {
        System.out.println("Root: yes!");
    }

    public void run() {
        System.out.println("Root: run!");
    }
}
