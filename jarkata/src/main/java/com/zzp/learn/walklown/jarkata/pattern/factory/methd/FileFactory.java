package com.zzp.learn.walklown.jarkata.pattern.factory.methd;

import com.zzp.learn.walklown.jarkata.pattern.factory.FactoryBean;
import com.zzp.learn.walklown.jarkata.pattern.factory.FileBean;

public class FileFactory implements IMethodFactory {
    @Override
    public FactoryBean initBean() {
        return new FileBean();
    }
}
