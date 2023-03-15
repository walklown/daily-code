package com.walklown.learn.jarkata.pattern.factory;

public class FileBean implements FactoryBean {
    @Override
    public void doServlet() {
        System.out.println("处理文件");
    }
}
