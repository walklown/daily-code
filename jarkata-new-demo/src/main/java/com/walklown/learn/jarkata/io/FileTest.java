package com.walklown.learn.jarkata.io;

import java.io.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FileTest {

    private static ExecutorService threadPoolExecutor = Executors.newWorkStealingPool();

    public static void main(String[] args) {

        FileOutputStream out = null;
        FileOutputStream outSTr = null;
        BufferedOutputStream Buff = null;
        FileWriter fw = null;
        RandomAccessFile raf = null;

        int count = 1000;//写文件行数

        try {
            //经过测试：FileOutputStream执行耗时:17，6，10 毫秒
            out = new FileOutputStream(new File("D:\\add.txt"));
            long begin = System.currentTimeMillis();
            out.write("测试java 文件操作1\r\n".getBytes());
            for (int i = 0; i < count; i++) {
                out.write("测试java 文件操作\r\n".getBytes());
            }
            out.close();
            long end = System.currentTimeMillis();
            System.out.println("FileOutputStream执行耗时:" + (end - begin) + " 毫秒");

            //经过测试：ufferedOutputStream执行耗时:1,1，1 毫秒
            outSTr = new FileOutputStream(new File("D:\\add0.txt"));
            Buff = new BufferedOutputStream(outSTr);
            long begin0 = System.currentTimeMillis();
            Buff.write("测试java 文件操作1\r\n".getBytes());
            for (int i = 0; i < count; i++) {
                Buff.write("测试java 文件操作\r\n".getBytes());
            }
            Buff.flush();
            Buff.close();
            long end0 = System.currentTimeMillis();
            System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");

            //经过测试：FileWriter执行耗时:3,9，5 毫秒
            fw = new FileWriter("D:\\add1.txt");
            long begin3 = System.currentTimeMillis();
            fw.write("测试java 文件操作1\r\n");
            for (int i = 0; i < count; i++) {
                fw.write("测试java 文件操作\r\n");
            }
            fw.close();
            long end3 = System.currentTimeMillis();
            System.out.println("FileWriter执行耗时:" + (end3 - begin3) + " 毫秒");

            raf = new RandomAccessFile("D:\\add2.txt", "rw");
            raf.setLength(1 * 1024 * 1024 * 1024);
            long begin4 = System.currentTimeMillis();
            raf.write("测试java 文件操作2\r\n".getBytes());
            raf.close();
            AtomicInteger j = new AtomicInteger(0);
            AtomicInteger num = new AtomicInteger(0);
            for (int i = 0; i < count; i++) {
                CompletableFuture future = CompletableFuture.supplyAsync(() -> {
                    RandomAccessFile raf1 = null;
                    try {
                        raf1 = new RandomAccessFile("D:\\add2.txt", "rw");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return raf1;
                }, threadPoolExecutor).thenAccept(raf1 -> {
                    if (raf1 != null) {
                        try {
                            int offset = j.incrementAndGet();
                            raf1.seek(offset * "测试java 文件操作\r\n".length());
                            raf1.write("测试java 文件操作\r\n".getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            raf1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).whenComplete((v, t) -> {
                    if (t != null) {
                        t.printStackTrace();
                    }
                    num.incrementAndGet();
                });
            }
            while (num.get() != count) {
//                System.out.println(num.get());
            }
            long end4 = System.currentTimeMillis();
            System.out.println("FileWriter执行耗时:" + (end4 - begin4) + " 毫秒");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
                Buff.close();
                outSTr.close();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}