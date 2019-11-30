package com.zzp.learn.walklown.jarkata.pattern.factory.methd;

import com.zzp.learn.walklown.jarkata.pattern.factory.FactoryBean;
import com.zzp.learn.walklown.jarkata.pattern.factory.LoginBean;

public class LoginFactory implements IMethodFactory {
    @Override
    public FactoryBean initBean() {
        return new LoginBean();
    }
}
