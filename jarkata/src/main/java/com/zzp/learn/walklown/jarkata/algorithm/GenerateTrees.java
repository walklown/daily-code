package com.zzp.learn.walklown.jarkata.algorithm;

import java.util.LinkedList;
import java.util.List;

class GenerateTrees {

    public static void main(String[] args) {
        List<TreeNode> treeNodes = generateTrees(3);
        for (TreeNode treeNode : treeNodes) {
            System.out.println(treeNode);
        }
    }

    private static List<TreeNode> generateTrees(int n) {
        return buildTree(1, n);
    }

    private static List<TreeNode> buildTree(int from, int to) {
        List<TreeNode> treeNodes = new LinkedList<>();
        if (from == to) {
            treeNodes.add(new TreeNode(from));
            return treeNodes;
        }
        for (int i = from; i < to + 1; i++) {
            List<TreeNode> leftTreeNodes = null;
            List<TreeNode> rightTreeNodes = null;
            if (i > from) {
                leftTreeNodes = buildTree(from, i - 1);
            }
            if (i < to) {
                rightTreeNodes = buildTree(i + 1, to);
            }
            if (leftTreeNodes != null) {
                if (rightTreeNodes != null) {
                    for (TreeNode leftTreeNode : leftTreeNodes) {
                        for (TreeNode rightTreeNode : rightTreeNodes) {
                            TreeNode node = new TreeNode(i);
                            node.left = leftTreeNode;
                            node.right = rightTreeNode;
                            treeNodes.add(node);
                        }
                    }
                } else {
                    for (TreeNode leftTreeNode : leftTreeNodes) {
                        TreeNode node = new TreeNode(i);
                        node.left = leftTreeNode;
                        treeNodes.add(node);
                    }
                }
            } else if (rightTreeNodes != null) {
                for (TreeNode rightTreeNode : rightTreeNodes) {
                    TreeNode node = new TreeNode(i);
                    node.right = rightTreeNode;
                    treeNodes.add(node);
                }
            }
        }
        return treeNodes;
    }
}

class TreeNode {
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

    @Override
    public String toString() {
        return "" + this.val + "," + this.left + "," + this.right;
    }
}