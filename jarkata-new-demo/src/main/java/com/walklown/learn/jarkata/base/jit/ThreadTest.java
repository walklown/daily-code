package com.walklown.learn.jarkata.base.jit;

import java.io.File;

/*
 * -XX:+PrintCompilation
 *
 * 1、由于 fag 变量是类变量，类变量的数据存在 Class 对象中，即本例子的引导类(假设为 A)，该类对象从 JDK 8开始属于元空间 (Meta-Space)
 * 也就是说它也属于 Heap 内存的一部分。图二所示，当 System.out 执行println 方法时，该方法对 System.out 对象增加 synchronized 关
 * 键字，当线程执行到此时，它去读 Meta-Space 中的数据，而 Meta-Space 有一个 Class 字典保存了所有的 Cass 对象，字典相当于一个 Map，
 * 其 Key 则是 Cass 全名称，当 System.out 执行时，先 Load System.class此时 Class 字曲在synchronized 的作用下，会去同步 CPU 本
 * 地缓存Web search flag 变量。尽管引导类A 未被加锁，然而其类变量 flag 在JMM 作用下被最终可见，所以图二不会被死循环。
 * 2、JIT 编译优化
 *
 * @author walklown
 * @date 2023/3/21
 */
public class ThreadTest {

    static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (!flag) {
//                System.out.println(flag);
//                echo(flag);
            }
        }).start();

        Thread.sleep(100);

        new Thread(()->{
            flag = true;
//            System.out.println("changed");
        }).start();
    }

    /**
     * java.lang.System.class、java.io.File.class 均属于同一个 ClassLoader - Bootstrap ClassLoader
     * @param value
     */
    private static void echo(Object value) {
        synchronized (File.separator) {
            flag = true;
        }
    }
}
