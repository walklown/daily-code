package com.zzp.pattern.factory.methd;

import com.zzp.pattern.factory.FactoryBean;

public class MFTest {
    public static void main(String[] args) {
        IMethodFactory factory = new FileFactory();
        FactoryBean bean = factory.initBean();
        bean.doServlet();
    }
}
