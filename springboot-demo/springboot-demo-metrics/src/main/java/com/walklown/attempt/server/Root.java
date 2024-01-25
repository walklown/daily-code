package com.walklown.attempt.server;

import org.springframework.stereotype.Component;

@Component
public class Root {

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
