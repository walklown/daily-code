package com.zzp.learn.walklown.jarkata.pattern.factory.abstr;

import com.zzp.pattern.factory.FactoryBean;

public interface IAbstractFactory {

    FactoryBean initFileFactory();

    FactoryBean initLoginFactory();
}
