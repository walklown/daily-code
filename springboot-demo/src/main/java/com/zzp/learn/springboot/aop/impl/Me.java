package com.zzp.learn.springboot.aop.impl;

import org.springframework.stereotype.Service;

@Service
public class Me implements Sleepable{
    @Override
    public void sleep() {
        System.out.println("\n睡觉！不休息哪里有力气学习！\n");  
    }  
} 