package com.walklown.learn.algorithm;

/**
 * 三个线程轮流输出ABC
 *
 * @author 守愚（张智沛）
 */
public class Train1 {

    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            while (true) {
                System.out.println("A");
                try {
                    Thread.sl
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread b = new Thread();
        Thread c = new Thread();
    }
}
