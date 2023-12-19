package com.zzp.learn.springboot.aop.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class Me implements Sleepable {

    @Autowired
    private Me1 me1;

    @Override
    public void sleep() {
        System.out.println("\n睡觉！\n");
    }  
} 