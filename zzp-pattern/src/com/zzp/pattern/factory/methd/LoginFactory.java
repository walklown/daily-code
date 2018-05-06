package com.zzp.pattern.factory.methd;

import com.zzp.pattern.factory.FactoryBean;
import com.zzp.pattern.factory.LoginBean;

public class LoginFactory implements IMethodFactory{
    @Override
    public FactoryBean initBean() {
        return new LoginBean();
    }
}
