package com.walklown.learn.jarkata.juc;

public class DumpThreadDemo {

    public static void main(String[] args) {
        // Thread API
        Thread.dumpStack();
        // StackTrace
        new Exception().printStackTrace();

        //JAVA 9
        StackWalker stackWalker = StackWalker.getInstance();
        stackWalker.forEach(System.out::println);
    }
}
