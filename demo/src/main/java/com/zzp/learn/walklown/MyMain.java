package com.zzp.learn.walklown;

import com.zzp.learn.walklown.algorithm.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class MyMain {

    public static void main(String[] args) {
//        Double a = null;
//        Node b = new Node();
//        b.a = null;
//        Object s = a != null ? a.doubleValue() : b.a;
        List<String> objs = new ArrayList<>();
        objs.add("1");
        objs.add("2");
        objs.add("3");
        log(objs.toArray());
    }

    public static class Node {
        public Double a;
    }

    private static void log(Object... obj) {
        for (Object o : obj) {
            System.out.println(o);
        }
    }
}
