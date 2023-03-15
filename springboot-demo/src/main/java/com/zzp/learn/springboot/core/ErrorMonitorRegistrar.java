/*
 * yingyinglicai.com Inc.
 * Copyright (c) 2013-2020 All Rights Reserved.
 */
package com.zzp.learn.springboot.core;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * @author shoujing
 * @date 2020/4/9 17:13
 */
public class ErrorMonitorRegistrar implements GenericApplicationListener {

    public static final int DEFAULT_ORDER = Ordered.HIGHEST_PRECEDENCE + 10;

    private static Class<?>[] EVENT_TYPES = {ApplicationStartingEvent.class,
            ApplicationEnvironmentPreparedEvent.class, ApplicationPreparedEvent.class,
            ContextClosedEvent.class, ApplicationFailedEvent.class};

    private static Class<?>[] SOURCE_TYPES = {SpringApplication.class,
            ApplicationContext.class};

    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {
        return isAssignableFrom(resolvableType.getRawClass(), EVENT_TYPES);
    }

    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return isAssignableFrom(sourceType, SOURCE_TYPES);
    }

    private boolean isAssignableFrom(Class<?> type, Class<?>... supportedTypes) {
        if (type != null) {
            for (Class<?> supportedType : supportedTypes) {
                if (supportedType.isAssignableFrom(type)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            ConfigurableEnvironment envi = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
            MutablePropertySources mps = envi.getPropertySources();
            PropertySource<?> ps = mps.get("applicationConfigurationProperties");
            if (ps != null && ps.containsProperty("env")) {
                String env = (String) ps.getProperty("env");
                MDC.put("env", env);
            } else {
                MDC.put("env", Strings.EMPTY);
            }
            if (ps != null && ps.containsProperty("log.monitor.mobiles")) {
                String mobiles = (String) ps.getProperty("log.monitor.mobiles");
                MDC.put("mobiles", mobiles);
            } else {
                MDC.put("mobiles", Strings.EMPTY);
            }
        }
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}