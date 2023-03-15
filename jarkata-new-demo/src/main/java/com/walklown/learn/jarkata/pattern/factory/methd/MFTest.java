package com.walklown.learn.jarkata.pattern.factory.methd;

import com.walklown.learn.jarkata.pattern.factory.FactoryBean;

public class MFTest {
    public static void main(String[] args) {
        IMethodFactory factory = new FileFactory();
        FactoryBean bean = factory.initBean();
        bean.doServlet();
    }
}
