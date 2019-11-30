package com.zzp.learn.walklown.jarkata.pattern.factory.abstr;

import com.zzp.learn.walklown.jarkata.pattern.factory.FactoryBean;
import com.zzp.learn.walklown.jarkata.pattern.factory.FileBean;
import com.zzp.learn.walklown.jarkata.pattern.factory.LoginBean;

public class AbstractFactory implements IAbstractFactory {
    @Override
    public FactoryBean initFileFactory() {
        return new FileBean();
    }

    @Override
    public FactoryBean initLoginFactory() {
        return new LoginBean();
    }
}
