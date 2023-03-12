package com.walklown.learn.algorithm.archive220204;

import com.walklown.learn.algorithm.base.TreeNode;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * @author shoujing
 * @date 2020/5/5 15:15
 */
public class CheckTree {

    public static void main(String[] args) {
        CheckTree checkTree = new CheckTree();
        System.out.println(checkTree.isValidBST(checkTree.root));
    }

    private TreeNode root;

    public CheckTree() {
        root = new TreeNode(3);

        root.left = new TreeNode(1);
        root.right = new TreeNode(5);

        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(6);

        root.left.right.right = new TreeNode(3);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return validate(root, null, null);
    }

    public boolean validate(TreeNode root, Integer leftLimit, Integer rightLimit) {
        if (root.left != null) {
            if (!(root.left.val < root.val
                    && (leftLimit == null || root.left.val > leftLimit)
                    && validate(root.left, leftLimit, root.val))) {
                return false;
            }
        }
        if (root.right != null) {
            if (!(root.right.val > root.val
                    && (rightLimit == null || root.right.val < rightLimit)
                    && validate(root.right, root.val, rightLimit))) {
                return false;
            }
        }
        return true;
    }
}

