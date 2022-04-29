package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k 是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 给你这个链表：1->2->3->4->5
 * <p>
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * <p>
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 *  
 * <p>
 * 说明：
 * <p>
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shoujing
 * @date 2020/5/16 16:35
 */
class ReverseKGroup {

    private static ListNode head;

    public ReverseKGroup() {
        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
    }

    public static void main(String[] args) {
        ReverseKGroup group = new ReverseKGroup();
        ListNode result = group.reverseKGroup(ReverseKGroup.head, 5);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode[] back = new ListNode[k];
        back[0] = head;
        for (int i = 1; i < k; i++) {
            if (back[i - 1].next != null) {
                back[i] = back[i - 1].next;
            } else {
                return head;
            }
        }
        if (back[k - 1].next != null) {
            back[0].next = reverseKGroup(back[k - 1].next, k);
        } else {
            back[0].next = null;
        }
        for (int i = k - 1; i > 0; i--) {
            back[i].next = back[i - 1];
        }
        return back[k - 1];
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}