package com.zzp.learn.springboot.aop1.aggregate;

import jakarta.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Configuration
public class CustomBeanFactory {

    @Autowired
    protected AutowireCapableBeanFactory beanFactory;

    @Bean(autowireCandidate = false)
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Transactional(rollbackFor = Exception.class)
    public Aggregate wire(String phone) {
        return new Aggregate(phone);
    }
}
