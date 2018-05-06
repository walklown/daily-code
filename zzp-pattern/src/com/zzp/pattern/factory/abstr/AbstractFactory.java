package com.zzp.pattern.factory.abstr;

import com.zzp.pattern.factory.FactoryBean;
import com.zzp.pattern.factory.FileBean;
import com.zzp.pattern.factory.LoginBean;

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
