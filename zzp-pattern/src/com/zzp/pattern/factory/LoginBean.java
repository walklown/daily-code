package com.zzp.pattern.factory;

public class LoginBean implements FactoryBean {
    @Override
    public void doServlet() {
        System.out.println("用户登陆");
    }
}
