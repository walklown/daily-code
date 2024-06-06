package com.walklown.learn;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * All Algorithm
 *
 * @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class AlgorithmClass {

    public static void main(String[] args) {
        AlgorithmClass algorithmClass = new AlgorithmClass();
//        System.out.println(algorithmClass.zigzagLevelOrder(new int[]));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Integer>> nodeList = new ArrayList<>();
        nodeList.add(new ArrayList<>());
        leftFirst(root, nodeList, 0);
        return nodeList;
    }

    private void leftFirst(TreeNode root, List<List<Integer>> nodeList, int deep) {
        List<Integer> currentNodeList;
        if (nodeList.size() <= deep) {
            currentNodeList = new ArrayList<>();
            nodeList.add(currentNodeList);
        } else {
            currentNodeList = nodeList.get(deep);
        }
        if (deep % 2 == 1) {
            currentNodeList.addFirst(root.val);
        } else {
            currentNodeList.add(root.val);
        }
        if (root.left != null) {
            leftFirst(root.left, nodeList, deep + 1);
        }
        if (root.right != null) {
            leftFirst(root.right, nodeList, deep + 1);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
