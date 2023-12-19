package com.zzp.learn.springboot.aop.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentDemo {

    @Bean(name = "objectMapper1")
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
