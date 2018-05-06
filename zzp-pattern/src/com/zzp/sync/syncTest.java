package com.zzp.sync;

import com.zzp.sync.zzp.xiaoming;

public class syncTest {
    private xiaoming i = new xiaoming();
    public void method(){
        synchronized (i){
            System.out.println(1);
        }
    }
    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("1");
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("2");
        }
    });
    static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("3");
        }
    });
    public static void main(String[] args) throws InterruptedException {
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
    }
}
