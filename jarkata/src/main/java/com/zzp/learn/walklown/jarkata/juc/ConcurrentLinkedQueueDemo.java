package com.zzp.learn.walklown.jarkata.juc;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * https://mp.weixin.qq.com/s?__biz=MzI1NDQ3MjQxNA==&mid=2247492716&idx=1&sn=d48b552feadadb58a390aba662e44109&chksm=e9c61ddddeb194cb842c5e4b4feeae9670b93bc11d1102d5744c886367887256097fa2298b13&mpshare=1&scene=23&srcid=08097tqRsJH3EudCvnHJkV83&sharer_sharetime=1596952503098&sharer_shareid=aaf90ca58f70c6872493c452f8c84289#rd
 *
 * JDK代码修复后，删除最后一个节点还是会留有一个 ITEM 为 NULL 的节点未删除，在下次 remove 操作时，才会将遍历到的
 * ITEM 为 NULL 的值删除
 *
 * VisualVM、jconsole、jmc
 *
 * @author walklown
 * @date 2020/8/9 18:01
 */
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentLinkedQueue<Integer> linkedQueue = new ConcurrentLinkedQueue<>();
        Integer one = Integer.valueOf(1);
        Integer two = new Integer(2);
        Integer three = new Integer(3);
        linkedQueue.offer(one);
        linkedQueue.offer(two);
        linkedQueue.offer(three);
        Runtime runtime = Runtime.getRuntime();
        long last = System.currentTimeMillis();
        Thread.sleep(20000);
        for (int i = 4; i < 10000; i++) {
            Integer item = new Integer(i);
            linkedQueue.offer(item);
            linkedQueue.remove(item);
            System.err.printf("duration:%d q.size=%d memery max=%d free=%d total=%d\n",
                    System.currentTimeMillis() - last, linkedQueue.size(), runtime.maxMemory(),
                    runtime.freeMemory(), runtime.totalMemory());
            last = System.currentTimeMillis();
        }
        linkedQueue.remove(two);
        System.out.println(linkedQueue);
    }
}
