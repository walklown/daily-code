package com.zzp.learn.walklown.jarkata.pattern.factory.abstr;

import com.zzp.pattern.factory.FactoryBean;

public class AFTest {
    public static void main(String[] args) {
        IAbstractFactory factory = new AbstractFactory();
        FactoryBean bean = factory.initLoginFactory();
        bean.doServlet();
    }
}
