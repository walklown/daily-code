package com.walklown.learn.jarkata.pattern;

public class Seriable {
    /**
     * 序列化
     * 序列化就是说把内存中的状态转化成字节码的形式
     * 从而转换一个IO流，写入到其他地方（可以是磁盘，网络IO）
     * 将内存中的状态永久保存
     */
    /**
     * 反序列化
     * 将已经持久化的字节码内容转化为IO流
     * 通过IO流的读取，进而将读取的内容转化为Java对象
     * 在转化过程中会重新创建对象new
     */
    public final static Seriable INSTANCE = new Seriable();

    private Seriable() {
    }

    public static Seriable getInstance() {
        return INSTANCE;
    }

    public static Seriable readResolve() {
        return INSTANCE;
    }
}
