package com.zzp.learn.walklown.jarkata.algorithm;

import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class LevelOrder {

    public static void main(String[] args) {
        LevelOrder levelOrder = new LevelOrder();
        System.out.println(levelOrder.levelOrder(levelOrder.root));
    }

    private TreeNode root;

    public LevelOrder() {
        root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
    }

    // TODO 如果 LinkedList 本身开放向前向后索引的能力，这里应该可以直接使用正遍历或者中遍历，也就不需要辅助队列了
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        List<List<Integer>> result = new LinkedList<>();
        LinkedList<Integer> currentLevel = new LinkedList<>();
        LinkedList<TreeNode> currentLevelNode = new LinkedList<>();
        currentLevel.add(root.val);
        currentLevelNode.add(root);
        result.add(currentLevel);
        currentLevel = new LinkedList<>();
        while (!currentLevelNode.isEmpty()) {
            int currentLevelSize = currentLevelNode.size();
            for (int i = 0; i < currentLevelSize; i++) {
                TreeNode treeNode = currentLevelNode.poll();
                if (treeNode.left != null) {
                    currentLevelNode.add(treeNode.left);
                    currentLevel.add(treeNode.left.val);
                }
                if (treeNode.right != null) {
                    currentLevelNode.add(treeNode.right);
                    currentLevel.add(treeNode.right.val);
                }
            }
            if (currentLevel.isEmpty()) {
                break;
            }
            result.add(currentLevel);
            currentLevel = new LinkedList<>();
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public class MyList extends AbstractList {

        @Override
        public Object get(int index) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }
    }
}
