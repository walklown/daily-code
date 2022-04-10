package com.zzp.learn.walklown;

import com.zzp.learn.walklown.algorithm.base.TreeNode;

public class MyMain {

    public static void main(String[] args) {
        Double a = null;
        Node b = new Node();
        b.a = null;
        Object s = a != null ? a.doubleValue() : b.a;
    }

    public static class Node {
        public Double a;
    }
}
