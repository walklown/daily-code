package com.walklown.learn.jarkata.base;

public class NestClassDemo {

    private String a;

    public String getAa() {
        InnerClass innerClass = new InnerClass();
        return innerClass.aa;
    }

    public class InnerClass {

        private String aa;

        public String getA() {
            NestClassDemo nestClassDemo = new NestClassDemo();
            return nestClassDemo.a;
        }

    }
}
