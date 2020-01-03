package com.zzp.learn.walklown.jarkata.juc.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

/**
 * 当一个线程需要获取锁时：
 * 1.创建一个的QNode，将其中的locked设置为true表示需要获取锁
 * 2.线程对tail域调用getAndSet方法，使自己成为队列的尾部，同时获取一个指向其前趋结点的引用myPred
 * 3.该线程就在前趋结点的locked字段上旋转，直到前趋结点释放锁
 * 4.当一个线程需要释放锁时，将当前结点的locked域设置为false，同时回收前趋结点
 *
 * CLH队列锁的优点是空间复杂度低（如果有n个线程，L个锁，每个线程每次只获取一个锁，那么需要的存储空间是O（L+n），n个线程有n个
 * myNode，L个锁有L个tail），CLH的一种变体被应用在了JAVA并发框架中(AbstractQueuedSynchronizer.Node)。CLH在SMP系统结构下
 * 该法是非常有效的。但在NUMA系统结构下，每个线程有自己的内存，如果前趋结点的内存位置比较远，自旋判断前趋结点的locked域，性能
 * 将大打折扣，一种解决NUMA系统结构的思路是MCS队列锁。
 *
 * @author shoujing
 * @date 2019/12/29 17:22
 * @see StampedLock
 */
public class CLHLock implements Lock {
    AtomicReference<QNode> tail;
    ThreadLocal<QNode> myPred;
    ThreadLocal<QNode> myNode;

    public CLHLock() {
        tail = new AtomicReference<>(new QNode());
        myNode = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return new QNode();
            }
        };
        myPred = new ThreadLocal<QNode>() {
            protected QNode initialValue() {
                return null;
            }
        };
    }

    @Override
    public void lock() {
        QNode qnode = myNode.get();
        qnode.locked = true;
        QNode pred = tail.getAndSet(qnode);
        myPred.set(pred);
        while (pred.locked) {
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        QNode qnode = myNode.get();
        qnode.locked = false;
        myNode.set(myPred.get());
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}

class QNode {
    boolean locked = false;
    QNode next = null;
}