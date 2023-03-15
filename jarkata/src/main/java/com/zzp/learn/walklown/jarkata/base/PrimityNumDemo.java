package com.zzp.learn.walklown.jarkata.base;

public class PrimityNumDemo {

    public static void main(String[] args) {
        byte a = 127;
        byte b = 127;
//        b = a + b; can`t convert int to byte
        b += a;
    }

    public void switchTest(String a) {
        // java 7 开始支付字符串
        switch (a) {
            case "a":
                System.out.println(a);
                break;
            case "b":
                System.out.println("b");
                break;
            default:
                System.out.println("default");
        }
    }
}
