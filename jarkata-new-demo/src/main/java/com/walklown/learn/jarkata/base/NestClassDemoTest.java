package com.walklown.learn.jarkata.base;

public class NestClassDemoTest {
    
    public static void main(String[] args) {
        NestClass nestClass = new NestClass();
        NestClass.InnerClass innerClass = nestClass.getInnerClass();
        System.out.println(innerClass);
        System.out.println(innerClass.getA());
        System.out.println(innerClass.getA());
    }
}
