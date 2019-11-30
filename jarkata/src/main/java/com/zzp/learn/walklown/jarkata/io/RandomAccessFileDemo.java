package com.zzp.learn.walklown.jarkata.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomAccessFileDemo {

    private static final int count = 1000;//写文件行数

    public static void main(String[] args) {
        //经过测试：FileOutputStream执行耗时:17，6，10 毫秒
        try (RandomAccessFile raf = new RandomAccessFile("D:\\add2.txt", "rw");) {
            raf.setLength(1 * 1024 * 1024 * 1024);
            long begin4 = System.currentTimeMillis();
            raf.write("测试java 文件操作2\r\n".getBytes());
            raf.close();
            AtomicInteger j = new AtomicInteger(0);
            for (int i = 0; i < count; i++) {
                try {
                    int offset = j.incrementAndGet();
                    raf.seek(offset * "测试java 文件操作\r\n".length());
                    raf.write("测试java 文件操作\r\n".getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            long end4 = System.currentTimeMillis();
            System.out.println("RandomAccessFile 执行耗时:" + (end4 - begin4) + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
