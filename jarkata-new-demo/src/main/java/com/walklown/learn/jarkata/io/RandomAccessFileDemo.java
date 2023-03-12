package com.walklown.learn.jarkata.io;

import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

    private static final int count = 1;//写文件行数

    public static void main(String[] args) {
        //经过测试：FileOutputStream执行耗时:17，6，10 毫秒
        try (RandomAccessFile raf = new RandomAccessFile("D:\\add2.txt", "rw");) {
            raf.setLength(1 * 1024 * 1024);
            long begin4 = System.currentTimeMillis();
            raf.write("first\r\n".getBytes());
            raf.seek("first\r\n".length() + 10);
            raf.write("second\r\n".getBytes());
            raf.seek("first\r\n".length());
            raf.write("???\n".getBytes());
            long end4 = System.currentTimeMillis();
            System.out.println("RandomAccessFile 执行耗时:" + (end4 - begin4) + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
