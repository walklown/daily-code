package com.walklown.learn.algorithm.archive220204;

import com.walklown.learn.algorithm.base.TreeNode;

/**
 * 二叉搜索树中的两个节点被错误地交换。
 * <p>
 * 请在不改变其结构的情况下，恢复这棵树。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,3,null,null,2]
 * <p>
 * 1
 * /
 * 3
 * \
 * 2
 * <p>
 * 输出: [3,1,null,null,2]
 * <p>
 * 3
 * /
 * 1
 * \
 * 2
 * 示例 2:
 * <p>
 * 输入: [3,1,4,null,null,2]
 * <p>
 * 3
 * / \
 * 1   4
 * /
 * 2
 * <p>
 * 输出: [2,1,4,null,null,3]
 * <p>
 * 2
 * / \
 * 1   4
 * /
 * 3
 * 进阶:
 * <p>
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 * @author walklown
 * @date 2020/8/8 16:26
 */
public class RecoverTree {

    public static void main(String[] args) {
        RecoverTree recoverTree = new RecoverTree();
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode.left = treeNode1;
        treeNode1.right = treeNode2;
        recoverTree.recoverTree(treeNode);
        System.out.println(treeNode);
    }

    private TreeNode t1, t2, pre = null;

    public void recoverTree(TreeNode root) {
        readNode(root);
        int cache = t1.val;
        t1.val = t2.val;
        t2.val = cache;
    }

    private void readNode(TreeNode node) {
        if (node == null) {
            return;
        }
        readNode(node.left);
        if (pre != null && pre.val > node.val) {
            if (t1 == null) {
                t1 = pre;
            }
            t2 = node;
        }
        pre = node;
        readNode(node.right);
    }
}