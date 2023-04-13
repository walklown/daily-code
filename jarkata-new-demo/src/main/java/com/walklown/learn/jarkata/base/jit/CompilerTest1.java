package com.walklown.learn.jarkata.base.jit;

public class CompilerTest1 {

    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("a").append("b");
        String s = builder.toString();
        System.out.println(s);
    }
}
