package com.zzp.learn.springboot.aop2;

import org.springframework.stereotype.Component;

@Component
public class Child2 extends Root {

    @Override
    public void run() {
        System.out.println("Child2: run!");
    }
}