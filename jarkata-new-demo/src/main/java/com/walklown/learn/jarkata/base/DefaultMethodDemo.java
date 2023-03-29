package com.walklown.learn.jarkata.base;

public class DefaultMethodDemo {

}

//class ImplementClass implements DefaultMethod,DefaultMethod1 {
//
//}

interface DefaultMethod {
    
    default void tryDefault() {
        System.out.println("DefaultMethod.tryDefault");
    }
}

interface DefaultMethod1 {
    
    default void tryDefault() {
        System.out.println("DefaultMethod1.tryDefault");
    }
}