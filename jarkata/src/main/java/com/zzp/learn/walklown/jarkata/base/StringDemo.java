package com.zzp.learn.walklown.jarkata.base;

/**
 * 当我们使用new String构造器来创建字符串的时候，字符串的值会在堆中创建，而不会加入JVM的字符串池中。相反，使用字面值创建的String对象会被放入堆
 * 的PermGen段中。们需要显式调用String.intern()方法来将它放入字符串池中。
 *
 * @author shoujing
 * @date 2020/4/27 16:35
 */
public class StringDemo {

    public static void main(String[] args) {
        String test = new String("Test");
        String test1 = "Test";
        String test2 = test.intern();
        System.out.println(test == test1);
        System.out.println(test1 == test2);
        System.out.println(test == test2);
    }
}
