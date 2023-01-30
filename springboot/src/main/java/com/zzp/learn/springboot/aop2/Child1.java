package com.zzp.learn.springboot.aop2;

import org.springframework.stereotype.Component;

@Component
@A2
public class Child1 extends Parent {

    @Override
    public void sayYes() {
        System.out.println("Child1: yes!");
    }
}
