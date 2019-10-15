package com.zzp.learn.walklown.jarkata.mercyblitz.test;

public class Lazy {

    private static boolean initialized = false;

    static {
        System.out.println("当前线程：" + Thread.currentThread().getName());
        Thread t = new Thread(
                () -> initialized = true
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println("子线程：" + Thread.currentThread().getName());
//                        initialized = true;
//                    }
//                }
        );
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(initialized);
    }
}