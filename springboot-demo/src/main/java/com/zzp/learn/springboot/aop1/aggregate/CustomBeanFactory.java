package com.zzp.learn.springboot.aop1.aggregate;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Optional;

@Configuration
public class CustomBeanFactory {

    @Bean(autowireCandidate = false)
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Aggregate wire(String phone) {
        return new Aggregate(phone);
    }
}
