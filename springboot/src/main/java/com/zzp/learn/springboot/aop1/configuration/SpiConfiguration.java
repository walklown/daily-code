package com.zzp.learn.springboot.aop1.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpiConfiguration {

    @Bean
    public ObjectMapper jacksonUtils() {
        return new ObjectMapper();
    }
}
