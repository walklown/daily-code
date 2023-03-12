package com.walklown.learn.jarkata.juc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListDemo {

    /**
     * CopyOnWriteArrayList在线程对其进行变更操作的时候，会拷贝一个新的数组以存放新的字段，因此写操作性能很差
     *
     * @author shoujing
     * @date 2020/4/26 19:15
     */
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new LinkedList<>());
        List<String> list1 = new CopyOnWriteArrayList();
    }
}
