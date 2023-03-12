package com.walklown.learn.jarkata.pattern.factory.methd;

import com.walklown.learn.jarkata.pattern.factory.FactoryBean;
import com.walklown.learn.jarkata.pattern.factory.FileBean;

public class FileFactory implements IMethodFactory {
    @Override
    public FactoryBean initBean() {
        return new FileBean();
    }
}
