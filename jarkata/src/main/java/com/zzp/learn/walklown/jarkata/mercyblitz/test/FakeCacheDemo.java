package com.zzp.learn.walklown.jarkata.mercyblitz.test;

import java.io.PrintStream;

public class FakeCacheDemo {

    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        method(a, b);
        System.out.println("a=" + a);
        System.out.println("b=" + b);
    }

    private static void method(Integer a, Integer b) {
        System.setOut(new PrintStream(System.out) {
            @Override
            public void println(String value) {
                switch (value) {
                    case "a=10":
                        super.println("a=100");
                        break;
                    case "b=10":
                        super.println("b=200");
                        break;
                    default:
                        super.println(value);
                }
            }
        });
    }
}
