package com.zzp.learn.springboot.aop.impl;

import org.springframework.stereotype.Service;

@Service
public class Me1 {

//    public Me1(Me me) {
//        System.out.println(me);
//    }

    public void sleep() {
        System.out.println("\n睡觉！\n");
    }  
} 