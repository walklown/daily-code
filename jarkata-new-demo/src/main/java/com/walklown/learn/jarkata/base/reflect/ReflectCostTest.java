package com.walklown.learn.jarkata.base.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 老版本反射
 *
 * @author 守愚（张智沛）
 */
public class ReflectCostTest {

    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Method m = Person.class.getDeclaredMethod("getName", new Class[] {});
        System.out.println(m.isAccessible());
        // getName是public的,猜猜输出是true还是false

        Person a = new Person();
        a.setName("Mr Lee");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            m.invoke(a, new Object[] {});
        }
        System.out.println("Simple              :" + (System.currentTimeMillis() - start));

        m.setAccessible(true); // 注意此处不同
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            m.invoke(a, new Object[] {});
        }
        System.out.println("setAccessible(true) :" + (System.currentTimeMillis() - start1));
    }
}
