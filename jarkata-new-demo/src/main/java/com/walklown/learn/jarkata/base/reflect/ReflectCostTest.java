package com.walklown.learn.jarkata.base.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * java.lang.reflect
 *
 * @author 守愚（张智沛）
 */
public class ReflectCostTest {

    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Method m = Person.class.getDeclaredMethod("getName");
        // getName是public的，isAccessible仍然是false
        System.out.println(m.isAccessible());

        // 1、直接invoke调用
        Person a = new Person();
        a.setName("Mr Lee");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            m.invoke(a);
        }
        System.out.println("Direct invoke       :" + (System.currentTimeMillis() - start));

        // 2、设置Accessible后再调用
        m.setAccessible(true); // 注意此处不同
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            m.invoke(a);
        }
        System.out.println("setAccessible(true) :" + (System.currentTimeMillis() - start1));
    }
}
