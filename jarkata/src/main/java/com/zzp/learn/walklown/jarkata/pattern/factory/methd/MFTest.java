package com.zzp.learn.walklown.jarkata.pattern.factory.methd;

import com.zzp.learn.walklown.jarkata.pattern.factory.FactoryBean;

public class MFTest {
    public static void main(String[] args) {
        IMethodFactory factory = new FileFactory();
        FactoryBean bean = factory.initBean();
        bean.doServlet();
    }
}
