package com.zzp.pattern;

import java.util.Calendar;
import java.util.Date;

public class DateDemo {
    public static void main(String[] args) {
        // Date date=new Date();
        System.out.println(new Date());
        long time = new Date().getTime();// 把年月日转化为毫秒,1s=1000ms 1秒=1000毫秒
        System.out.println(time);

        // 注意:下面一串数字默认为int类型,需要通过后加L强制转化为long类型才可以.
        // 输出一个固定不变的时间
        Date d = new Date(1441877203040L);// 把毫秒转化为年月日,
        System.out.println(d);

        // 比较时间的前后,返回的是boolean类型
        System.out.println(new Date().after(d));
        System.out.println(new Date().before(d));

        // 问题:Calendar 到底是不是单例模式? 争议:1不能new 2 c1==c2为false
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Runtime run1 = Runtime.getRuntime();
        Runtime run2 = Runtime.getRuntime();
        System.out.println("c1.equals(c2) is " + c1.equals(c2));
        System.out.println("c1 == c2 is " + (c1 == c2));

        System.out.println("run1.equals(run2) is " + run1.equals(run2));
        System.out.println("run1 == run2 is " + (run1 == run2));
        System.out.println(c1.getTime());
        System.out.println(c2.getTime());

    }
}
