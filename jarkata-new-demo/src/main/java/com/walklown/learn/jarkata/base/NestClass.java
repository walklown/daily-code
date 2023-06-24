package com.walklown.learn.jarkata.base;

public class NestClass {

    private String a;

    private InnerClass innerClass;

    public NestClass() {
        this.innerClass = new InnerClass("aa");
    }

    public String getAa() {
        return innerClass.aa;
    }

    public InnerClass getInnerClass() {
        return innerClass;
    }

    public class InnerClass {

        private String aa;

        public InnerClass(String aa) {
            this.aa = aa;
        }

        public String getA() {
            return NestClass.this.a;
        }

        public NestClass getNestClassDemo() {
            return NestClass.this;
        }

    }
}
