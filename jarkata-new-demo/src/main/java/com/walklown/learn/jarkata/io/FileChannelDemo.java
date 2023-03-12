package com.walklown.learn.jarkata.io;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {

    public static void main(String[] args) {
        try (FileChannel fileChannel = new RandomAccessFile(new File("D://db.data"), "rw").getChannel();) {
            // 写
            byte[] data = new byte[4096];
            long position = 1024L;
            // 从当前文件指针的位置写入 4kb 的数据
            fileChannel.write(ByteBuffer.wrap(data));
            // 指定 position 写入 4kb 的数据
            fileChannel.write(ByteBuffer.wrap(data), position);

            // 读
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            // 指定 position 读取 4kb 的数据
            fileChannel.read(buffer, position);
            // 从当前文件指针的位置读取 4kb 的数据
            fileChannel.read(buffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
