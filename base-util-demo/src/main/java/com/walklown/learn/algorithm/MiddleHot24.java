package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

/**
 * 24. 两两交换链表中的节点
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot24 {

    public static void main(String[] args) {
        MiddleHot24 middleHot = new MiddleHot24();
        System.out.println(JacksonUtils.toJSONString(middleHot.swapPairs(buildListNode(new int[]{1, 2, 3, 4}))));
    }

    private static ListNode buildListNode(int[] num) {
        ListNode first = new ListNode();
        first.val = num[0];
        ListNode offset = first;
        for (int i = 1; i < num.length; i++) {
            ListNode next = new ListNode();
            next.val = num[i];
            offset.next = next;
            offset = next;
        }
        return first;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prefix = new ListNode();
        prefix.next = head;
        ListNode pre = prefix;
        ListNode link = head;
//        head = head.next;
        ListNode first;
        while (link != null && link.next != null) {
            first = link;
            link = first.next.next;
            pre.next = first.next;
            first.next.next = first;
            pre = first;
            pre.next = first;
            first.next = link;
        }
        return prefix.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
