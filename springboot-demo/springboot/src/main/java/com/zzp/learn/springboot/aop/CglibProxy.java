package com.zzp.learn.springboot.aop;

import com.zzp.learn.springboot.aop.impl.Me;
import com.zzp.learn.springboot.aop.impl.Sleepable;
import com.zzp.learn.springboot.aop.impl.Sleepable1;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    private Object targetObject;

    public CglibProxy(Object targetObject) {
        this.targetObject = targetObject;
    }

    public CglibProxy() {
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("开启事物" + obj.getClass());
        Object result = proxy.invoke(targetObject, args);
        System.out.println("关闭事物");
        // 返回代理对象
        return null;
    }

    public static void main(String[] args) {
//        CglibProxy cglibProxy = new CglibProxy();
        CglibProxy cglibProxy = new CglibProxy(new Me());
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{Sleepable.class, Sleepable1.class});
        enhancer.setContextClass(Sleepable.class);
//        enhancer.setSuperclass(Me.class);
        enhancer.setCallback(cglibProxy);
        Sleepable userDao = (Sleepable) enhancer.create();
        userDao.sleep();
    }
}
