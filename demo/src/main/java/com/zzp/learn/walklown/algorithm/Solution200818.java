package com.zzp.learn.walklown.algorithm;

import com.zzp.learn.walklown.algorithm.base.ListNode;
import com.zzp.learn.walklown.algorithm.base.TreeNode;


/**
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * @author walklown
 * @date 2020/8/18 23:48
 */
class Solution200818 {

    public static void main(String[] args) {
        Solution200818 solution = new Solution200818();
        ListNode first = new ListNode(0);
        Integer[] params = new Integer[]{-10, -3, 0, 5, 9};
        ListNode last = first;
        for (Integer param : params) {
            last.next = new ListNode(param);
            last = last.next;
        }
        System.out.println(solution.sortedListToBST(first.next));
    }

    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        } else if(head.next == null) return new TreeNode(head.val);
        ListNode pre = head;
        ListNode p = pre.next;
        ListNode q = p.next;
        //找到链表的中点p
        while(q!=null && q.next!=null){
            pre = pre.next;
            p = pre.next;
            q = q.next.next;
        }
        //将中点左边的链表分开
        pre.next = null;
        TreeNode root = new TreeNode(p.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(p.next);
        return root;
    }

}