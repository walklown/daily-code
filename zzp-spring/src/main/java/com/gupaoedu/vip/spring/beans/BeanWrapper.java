package com.gupaoedu.vip.spring.beans;

import com.gupaoedu.vip.spring.core.FactoryBean;

public class BeanWrapper extends FactoryBean {

    //观察者模式 支持事件响应，会有一个监听
    private BeanPostProcessor postProcessor;

    private Object wrapperInstance;

    private Object originalInstance;

    public BeanWrapper(Object instance, Object originalInstance) {
        this.wrapperInstance = instance;
        this.originalInstance = originalInstance;
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public Class<?> getWrapperClass(){
        return this.wrapperInstance.getClass();
    }

    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }
}
