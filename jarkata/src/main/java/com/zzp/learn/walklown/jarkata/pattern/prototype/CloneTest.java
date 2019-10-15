package com.zzp.learn.walklown.jarkata.pattern.prototype;

import java.util.ArrayList;

public class CloneTest {
    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        prototype.name = "zzp";
        prototype.list = new ArrayList();
        System.out.println(prototype.list);
        try {
            Prototype o = (Prototype) prototype.clone();
            System.out.println(o.list);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
