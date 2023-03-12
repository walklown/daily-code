package com.walklown.learn.jarkata.pattern.factory.abstr;

import com.walklown.learn.jarkata.pattern.factory.FactoryBean;

public class AFTest {
    public static void main(String[] args) {
        IAbstractFactory factory = new AbstractFactory();
        FactoryBean bean = factory.initLoginFactory();
        bean.doServlet();
    }
}
