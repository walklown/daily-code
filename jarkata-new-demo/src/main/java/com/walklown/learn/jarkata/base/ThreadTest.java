package com.walklown.learn.jarkata.base;

/*
 * -XX:+PrintCompilation
 *
 * @author walklown
 * @date 2023/3/21
 */
public class ThreadTest {

    static boolean flag = false;

    public static void main(String[] args) {
        new Thread(()->{
            while (!flag) {
//                System.out.println(flag);
            }
        }).start();

        new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            flag = true;
        }).start();
    }
}
