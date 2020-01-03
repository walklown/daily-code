package com.zzp.learn.walklown.jarkata.interviewing.jackson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 反射
 * Jackson
 * cas
 * 线程池
 *
 * @author shoujing
 * @date 2019/11/21 10:29
 */
public class reflect {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        A a = new A();
        a.setName("sss");
        List<Object> s = new LinkedList<>();
        Field field = A.class.getDeclaredFields()[0];
//        field.setAccessible(true);
        Method method = A.class.getMethod("get" + upperCapital(field.getName()));
        Date time1 = new Date();
        for (int i = 0; i < 10000; i++) {
            s.add(method.invoke(a));
        }
        Date time2 = new Date();
        System.out.println(s.get(0));
        Date time3 = new Date();
        List<String> s1 = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            s1.add(a.getName());
        }
        Date time4 = new Date();
        System.out.println(s1.get(0));
        System.out.println((time2.getTime()-time1.getTime()) + " " + (time4.getTime() - time3.getTime()));
    }

    private static String upperCapital(String name) {
        String first = name.substring(0, 1);
        String tail = name.substring(1);
        return first.toUpperCase() + tail;
    }
}

class A {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
