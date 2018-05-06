package com.zzp.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyA<T> implements InvocationHandler {

    private T item;

    public T getInstance(T item){
        this.item = item;
        T tt = (T) Proxy.newProxyInstance(item.getClass().getClassLoader(), item.getClass().getInterfaces(), this );
        return tt;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}
