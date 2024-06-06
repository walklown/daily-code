package com.zzp.learn.springboot.aop;

import org.springframework.context.event.GenericApplicationListener;
import org.springframework.context.event.SmartApplicationListener;

public class TestDemo {

    public static void main(String[] args) {
        System.out.println(SmartApplicationListener.class.isAssignableFrom(GenericApplicationListener.class));
    }
}
