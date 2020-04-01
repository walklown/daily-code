package com.gupaoedu.spring.demo.service.impl;


import com.gupaoedu.spring.demo.service.IDemoService;
import com.gupaoedu.spring.annotation.Service;

@Service
public class DemoService implements IDemoService {

    public String get(String name) {
        return "My name is " + name;
    }

}
