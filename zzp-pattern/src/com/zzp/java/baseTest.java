package com.zzp.java;

import java.util.Comparator;
import java.util.TreeMap;

public class baseTest {
//    public static void main(String[] args) {
//        Short s0 = 128;
//        Short s1 = 127;
//        Short s2 = 127;
//        Short s3 = 0;
//        Short s4 = new Short((short) 127);
//        Short s5 = new Short((short) 127);
//        Short s6 = new Short((short) 0);
//
//        System.out.println("s0=s1:"+(s0==s1));
//        System.out.println("s1=s2:"+(s1==s2));
//        System.out.println("s1=s2+s3:"+(s1==s2+s3));
//        System.out.println("s1=s4:"+(s1==s4));
//        System.out.println("s4=s5:"+(s4==s5));
//        System.out.println("s4=s5+s6:"+(s4==s5+s6));
//
//    }

    public static void main(String[] args) {
        TreeMap<Comparable, Object> treeMap = new TreeMap<>(new Comparator<Comparable>() {
            @Override
            public int compare(Comparable o1, Comparable o2) {
                return 0;
            }
        });
        treeMap.put(2, 2);
        treeMap.put(1, 1);
        System.out.println(treeMap);
    }
}
