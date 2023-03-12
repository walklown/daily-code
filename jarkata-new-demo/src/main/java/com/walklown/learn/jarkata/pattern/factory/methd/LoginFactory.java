package com.walklown.learn.jarkata.pattern.factory.methd;

import com.walklown.learn.jarkata.pattern.factory.FactoryBean;
import com.walklown.learn.jarkata.pattern.factory.LoginBean;

public class LoginFactory implements IMethodFactory {
    @Override
    public FactoryBean initBean() {
        return new LoginBean();
    }
}
