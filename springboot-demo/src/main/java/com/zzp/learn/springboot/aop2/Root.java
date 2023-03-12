package com.zzp.learn.springboot.aop2;

import org.springframework.stereotype.Component;

@Component
@A1
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
