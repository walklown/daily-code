package com.walklown.learn.algorithm;

/**
 * 2. 两数相加
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot2 {

    public static void main(String[] args) {
        MiddleHot2 middleHot = new MiddleHot2();
//        System.out.println(middleHot.addTwoNumbers());
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode next1 = l1;
        ListNode next2 = l2;
        boolean up = false;
        while (true) {
            if (next2 != null) {
                next1.val += next2.val;
            }
            if (up) {
                next1.val++;
            }
            if (next1.val >= 10) {
                up = true;
                next1.val = next1.val - 10;
            } else {
                up = false;
            }
            if (next1.next == null) {
                if (next2 != null && next2.next != null) {
                    next1 = next2.next;
                    next2 = null;
                } else {
                    if (up) {
                        next1.next = new ListNode();
                        next1.next.val = 1;
                    }
                    break;
                }
            } else {
                next1 = next1.next;
                if (next2 != null) {
                    next2 = next2.next;
                }
            }
        }
        return l1;
    }

    public class ListNode {
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
