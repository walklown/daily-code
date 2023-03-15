package com.walklown.learn.jarkata.pattern.factory.abstr;

import com.walklown.learn.jarkata.pattern.factory.FactoryBean;

public interface IAbstractFactory {

    FactoryBean initFileFactory();

    FactoryBean initLoginFactory();
}
