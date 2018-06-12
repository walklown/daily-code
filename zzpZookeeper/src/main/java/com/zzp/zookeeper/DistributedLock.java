package com.zzp.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class DistributedLock implements Lock, Watcher {

    private ZooKeeper zk = null;
    private String ROOT_LOCK = "/lock"; //根节点
    private String WAIT_LOCK; //前一个锁
    private String CURRENT_LOCK; //前一个锁

    private CountDownLatch countDownLatch;

    public DistributedLock(){
        try {
            zk = new ZooKeeper("192.168.89.128:2181", 4000, this);
            //判断根节点是否存在
            Stat stat = zk.exists(ROOT_LOCK, false);
            if (stat==null){
                zk.create(ROOT_LOCK, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void lock() {
        if (this.tryLock()){
            System.out.println(Thread.currentThread()+"->"+CURRENT_LOCK+",获得锁成功");
        }
        waitForLock(WAIT_LOCK);
    }

    public boolean waitForLock(String prev){
        try {
            Stat stat = zk.exists(prev, true);
            if (stat!=null){
                System.out.println(Thread.currentThread()+"->等待"+ROOT_LOCK+"/"+prev+"释放");
                countDownLatch = new CountDownLatch(1);
                countDownLatch.await();
                System.out.println(Thread.currentThread()+"->"+CURRENT_LOCK+"获得锁");
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        try {
            CURRENT_LOCK = zk.create(ROOT_LOCK+"/", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(Thread.currentThread()+"->"+CURRENT_LOCK+",尝试竞争锁");
            List<String> childrens = zk.getChildren(ROOT_LOCK, false);
            SortedSet<String> sortedSet = new TreeSet<String>();
            childrens.forEach(children->{
                sortedSet.add(ROOT_LOCK+"/"+children);
            });
            String firstNode = sortedSet.first();
            if (CURRENT_LOCK.equals(firstNode)){
                return true;
            }
            SortedSet<String> lessThanMe = ((TreeSet<String>)sortedSet).headSet(CURRENT_LOCK);
            if (!lessThanMe.isEmpty()){
                WAIT_LOCK = lessThanMe.last();
            }
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        System.out.println(Thread.currentThread()+"->"+CURRENT_LOCK+"释放锁");
        try {
            zk.delete(CURRENT_LOCK, -1);
            CURRENT_LOCK = null;
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public Condition newCondition() {
        return null;
    }

    public void process(WatchedEvent watchedEvent) {
        if (countDownLatch!=null){
            countDownLatch.countDown();
        }
    }
}
