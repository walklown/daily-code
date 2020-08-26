package com.zzp.learn.walklown.algorithm;


/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 *
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class Solution200817 {

    public static void main(String[] args) {
        Solution200817 solution = new Solution200817();
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode7 = new TreeNode(4);
        TreeNode treeNode8 = new TreeNode(4);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;

        System.out.println(solution.isBalanced(treeNode));
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        try {
            getDeep(root);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public int getDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeep = getDeep(root.left);
        int rigthDeep = getDeep(root.right);
        int maxDeep;
        if (leftDeep > rigthDeep) {
            maxDeep = leftDeep;
            if (leftDeep - rigthDeep > 1) {
                // false
                throw new IllegalArgumentException();
            }
        } else {
            maxDeep = rigthDeep;
            if (rigthDeep - leftDeep > 1) {
                // false
                throw new IllegalArgumentException();
            }
        }
        return maxDeep + 1;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}