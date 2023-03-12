package com.zzp.learn.springboot.imports.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloWorldConfiguration {

    @Bean
    public String helloWorld() {
        return "hello, world";
    }
}
