package com.walklown.learn.jarkata.pattern.factory.abstr;

import com.walklown.learn.jarkata.pattern.factory.FactoryBean;
import com.walklown.learn.jarkata.pattern.factory.FileBean;
import com.walklown.learn.jarkata.pattern.factory.LoginBean;

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
