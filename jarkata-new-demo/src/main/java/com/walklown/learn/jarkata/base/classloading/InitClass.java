package com.walklown.learn.jarkata.base.classloading;

class InitClass {

    public InitClass() {
        System.out.println("InitClass 构造器");
    }

    static {
        System.out.println("InitClass静态代码块");
    }

    {
        System.out.println("InitClass代码块");
    }

    public static String a = null;

    public final static String b = "b";
    public String c = null;

    public static void method() {
        System.out.println("InitClass->method");
    }
}