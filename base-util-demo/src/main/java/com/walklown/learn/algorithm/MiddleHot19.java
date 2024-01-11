package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 19. 删除链表的倒数第 N 个结点
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot19 {

    public static void main(String[] args) {
        MiddleHot19 middleHot = new MiddleHot19();
        System.out.println(
                JacksonUtils.toJSONString(middleHot.removeNthFromEnd(buildListNode(new int[] {1}), 1)));
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode offset = head;
        ListNode beforeRemove = null;
        n--;
        while (offset.next != null) {
            if (n > 0) {
                n--;
            } else if (n == 0){
                beforeRemove = head;
                n--;
            } else {
                beforeRemove = beforeRemove.next;
            }
            offset = offset.next;
        }
        if (beforeRemove == null) {
            head = head.next;
        } else {
            beforeRemove.next = beforeRemove.next.next;
        }
        return head;
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
