package com.zzp.learn.walklown.jarkata.processor;

@Getter
public class Duck {

    private String name;

    public Duck(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Duck duck = new Duck("duck");
//        System.out.println(duck.get);
    }
}
