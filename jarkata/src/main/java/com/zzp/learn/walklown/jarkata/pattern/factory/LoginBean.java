package com.zzp.learn.walklown.jarkata.pattern.factory;

public class LoginBean implements FactoryBean {
    @Override
    public void doServlet() {
        System.out.println("用户登陆");
    }
}
