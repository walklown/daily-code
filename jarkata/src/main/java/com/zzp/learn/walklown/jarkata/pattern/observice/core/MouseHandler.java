package com.zzp.learn.walklown.jarkata.pattern.observice.core;

import com.zzp.pattern.observice.mouse.MouseEventCallback;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MouseHandler extends EventLisenter implements InvocationHandler {
    private Object target;

    public MouseHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String listenerMethod = "on" + uperFirstCase(method.getName());
        addLisenter(method.getName(), new MouseEventCallback(), MouseEventCallback.class.getMethod(listenerMethod, Event.class));
        method.invoke(target, args);
        System.out.println("代理执行监听");
        trigger(method.getName());
        return null;
    }

    private String uperFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }
}
