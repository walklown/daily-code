package com.zzp.learn.walklown.jarkata.mercyblitz.test;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author shoujing
 * @date 2019/10/29 14:21
 * 7. 【强制】所有整型包装类对象之间值的比较，全部使用equals方法比较。 说明：对于Integer var = ? 在-128至127 范围内的赋值，Integer 对象是在
 * IntegerCache.cache 产 生，会复用已有对象，这个区间内的 Integer 值可以直接使用==进行判断，但是这个区间之外的所有数 据，都会在堆上产生，并不会
 * 复用已有对象，这是一个大坑，推荐使用 equals方法进行判断。
 */
public class IntegerKeyMap {

    public static void main(String[] args) {
        Map map = new IdentityHashMap<>();
        map.put(1, "Hello");
        map.putIfAbsent(1, "World");
        print(map.get(1));
        print(map.size());

        map.put(1024, "A");
        map.putIfAbsent(1024, "B");
        print(map.get(1024));
        print(map.size());

        Integer i1 = Integer.valueOf(100);
        Integer i2 = Integer.valueOf(100);
        Byte b1 = Byte.valueOf((byte)100);
        Byte b2 = Byte.valueOf((byte)100);
        Byte b3 = i1.byteValue();
        print(i1 == i2);
        print(i1.equals(i2));

        print(b1 == b2);
        print(b1.equals(b2));

        print(i1.equals(b3));

        print(b1 == b3);
        print(b1.equals(b3));
    }

    private static void print(Object object) {
        System.out.print(object + "\n");
    }
}