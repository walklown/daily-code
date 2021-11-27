package com.zzp.learn.walklown;

import com.zzp.learn.walklown.test.DemoClass;

import java.lang.reflect.Method;
import java.util.Random;

public class AnnotationDemo {

//    public static void main(String[] args) {
//        for (Method method : DemoClass.class.getMethods()) {
//            System.out.println(method.getAnnotations());
//        }
//    }

    public static void main(String[] args) {
        System.out.println(Test1.a);
        System.out.println(Test1.b);
        System.out.println(Test2.a);
        System.out.println(Test2.b);
    }
}

class Test1 {
    static final int a = 1;
    static final int b = new Random().nextInt();
    static {
        System.out.println("init Test1");
    }
}

class Test2 {
    static final int a = 2;
    static int b = 3;
    static {
        System.out.println("init Test2");
    }
}
