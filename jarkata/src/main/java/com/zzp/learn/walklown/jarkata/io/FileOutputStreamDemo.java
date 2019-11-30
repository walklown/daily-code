package com.zzp.learn.walklown.jarkata.io;

import java.io.File;
import java.io.FileOutputStream;

public class FileOutputStreamDemo {

    private static final int count = 1000;//写文件行数

    public static void main(String[] args) {
        //经过测试：FileOutputStream执行耗时:17，6，10 毫秒
        try (FileOutputStream out = new FileOutputStream(new File("D:\\add.txt"))) {
            long begin = System.currentTimeMillis();
            out.write("测试java 文件操作1\r\n".getBytes());
            for (int i = 0; i < count; i++) {
                out.write("测试java 文件操作\r\n".getBytes());
            }
            long end = System.currentTimeMillis();
            System.out.println("FileOutputStream执行耗时:" + (end - begin) + " 毫秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
