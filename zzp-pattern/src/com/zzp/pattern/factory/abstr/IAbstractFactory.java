package com.zzp.pattern.factory.abstr;

import com.zzp.pattern.factory.FactoryBean;

public interface IAbstractFactory {

    FactoryBean initFileFactory();

    FactoryBean initLoginFactory();
}
