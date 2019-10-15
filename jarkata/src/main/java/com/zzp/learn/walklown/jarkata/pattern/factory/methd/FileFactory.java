package com.zzp.learn.walklown.jarkata.pattern.factory.methd;

import com.zzp.pattern.factory.FactoryBean;
import com.zzp.pattern.factory.FileBean;

public class FileFactory implements IMethodFactory {
    @Override
    public FactoryBean initBean() {
        return new FileBean();
    }
}
