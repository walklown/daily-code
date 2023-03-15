package com.zzp.learn.walklown.jarkata.base;

import org.openjdk.jol.info.ClassLayout;

public class LockClassDemo {

    public static void main(String[] args) {
//        System.out.println(ClassLayout.parseInstance(instance).toPrintable());
    }

    public synchronized void syncMethod() {
        synchronized (LockClassDemo.class) {

        }
    }
}
