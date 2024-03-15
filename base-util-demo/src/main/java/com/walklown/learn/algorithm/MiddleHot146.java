package com.walklown.learn.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 146. LRU 缓存
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot146 {

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(10);
        System.out.println(obj.get(10));
        obj.put(10, 13);
        obj.put(3, 17);
        obj.put(6, 11);
        obj.put(10, 5);
        obj.put(9, 10);
        System.out.println(obj.get(13));
        obj.put(2, 19);
        System.out.println(obj.get(2));
        System.out.println(obj.get(3));
        obj.put(5, 25);
        System.out.println(obj.get(8));
        obj.put(9, 22);
        obj.put(5, 5);
        obj.put(1, 30);
        System.out.println(obj.get(11));
        obj.put(9, 12);
        System.out.println(obj.get(7));
        System.out.println(obj.get(5));
        System.out.println(obj.get(8));
        System.out.println(obj.get(9));
        obj.put(4, 30);
        obj.put(9, 3);
        System.out.println(obj.get(9));
        System.out.println(obj.get(10));
        System.out.println(obj.get(10));
        obj.put(6, 14);
        obj.put(3, 1);
        System.out.println(obj.get(3));
        obj.put(10, 11);
        System.out.println(obj.get(8));
        obj.put(2, 14);
        System.out.println(obj.get(1));
        System.out.println(obj.get(5));
        System.out.println(obj.get(4));
        obj.put(11, 4);
        obj.put(12, 24);
        obj.put(5, 18);
        System.out.println(obj.get(13));
        obj.put(7, 23);
        System.out.println(obj.get(8));
        System.out.println(obj.get(12));
        obj.put(3, 27);
        obj.put(2, 12);
        System.out.println(obj.get(5));
        obj.put(2, 9);
        obj.put(13, 4);
        obj.put(8, 18);
        obj.put(1, 7);
        System.out.println(obj.get(6));
        obj.put(9, 29);
        obj.put(8, 21);
        System.out.println(obj.get(5));
        obj.put(6, 30);
        obj.put(1, 12);
        System.out.println(obj.get(10));
        obj.put(4, 15);
        obj.put(7, 22);
        obj.put(11, 26);
        obj.put(8, 17);
        obj.put(9, 29);
        System.out.println(obj.get(5));
        obj.put(3, 4);
        obj.put(11, 30);
        System.out.println(obj.get(12));
        obj.put(4, 29);
        System.out.println(obj.get(3));
        System.out.println(obj.get(9));
        System.out.println(obj.get(6));
        obj.put(3, 4);
        System.out.println(obj.get(1));
        System.out.println(obj.get(10));
        obj.put(3, 29);
        obj.put(10, 28);
        obj.put(1, 20);
        obj.put(11, 13);
        System.out.println(obj.get(3));
        obj.put(3, 12);
        obj.put(3, 8);
        obj.put(10, 9);
        obj.put(3, 26);
        System.out.println(obj.get(8));
        System.out.println(obj.get(7));
        System.out.println(obj.get(5));
        obj.put(13, 17);
        obj.put(2, 27);
        obj.put(11, 15);
        System.out.println(obj.get(12));
        obj.put(9, 19);
        obj.put(2, 15);
        obj.put(3, 16);
        System.out.println(obj.get(1));
        obj.put(12, 17);
        obj.put(9, 1);
        obj.put(6, 19);
        System.out.println(obj.get(4));
        System.out.println(obj.get(5));
        System.out.println(obj.get(5));
        obj.put(8, 1);
        obj.put(11, 7);
        obj.put(5, 2);
        obj.put(9, 28);
        System.out.println(obj.get(1));
        obj.put(2, 2);
        obj.put(7, 4);
        obj.put(4, 22);
        obj.put(7, 24);
        obj.put(9, 26);
        obj.put(13, 28);
        obj.put(11, 26);
    }

    public static class LRUCache {

        private int capacity;

        private Map<Integer, Item> cacheMap;

        private Item head;

        private Item tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            cacheMap = new HashMap<>(capacity);
        }

        public int get(int key) {
            Item item = cacheMap.get(key);
            if (item == null) {
                return -1;
            }
            if (head != item) {
                if (tail != item) {
                    item.pre.next = item.next;
                    item.next.pre = item.pre;
                } else {
                    item.pre.next = null;
                    tail = item.pre;
                }
                item.pre = null;
                item.next = head;
                head.pre = item;
                head = item;
            }
            return item.value;
        }

        public void put(int key, int value) {
            boolean contains = cacheMap.containsKey(key);
            if (cacheMap.size() == capacity && !contains) {
                // 驱逐tail
                Item beDel = tail;
                if (tail != head) {
                    tail = tail.pre;
                    tail.next = null;
                } else {
                    tail = null;
                    head = null;
                }
                cacheMap.remove(beDel.key);
            }
            // 新增
            Item item = new Item(key, value);
            Item old = cacheMap.put(key, item);
            item.next = head;
            head = item;
            if (tail == null) {
                tail = item;
            } else {
                item.next.pre = item;
            }
            if (old != null) {
                if (tail == old) {
                    old.pre.next = null;
                    tail = old.pre;
                } else {
                    old.pre.next = old.next;
                    old.next.pre = old.pre;
                }
            }
        }

        public static class Item {

            private final Integer key;

            private final Integer value;

            private Item pre;

            private Item next;

            public Item(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
        }
    }

}
