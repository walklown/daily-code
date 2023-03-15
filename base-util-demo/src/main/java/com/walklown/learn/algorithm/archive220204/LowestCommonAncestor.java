package com.walklown.learn.algorithm.archive220204;

import com.walklown.learn.algorithm.base.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 * <p>
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shoujing
 * @date 2020/5/10 21:10
 */
class LowestCommonAncestor {

    private static TreeNode root;
    private static TreeNode p;
    private static TreeNode q;

    public LowestCommonAncestor() {
        // root = [3,5,1,6,2,0,8,null,null,7,4]
        LowestCommonAncestor.root = new TreeNode(3);
        LowestCommonAncestor.root.left = new TreeNode(5);
        LowestCommonAncestor.root.right = new TreeNode(1);
        LowestCommonAncestor.root.left.left = new TreeNode(6);
        LowestCommonAncestor.root.left.right = new TreeNode(2);
        LowestCommonAncestor.root.right.left = new TreeNode(0);
        LowestCommonAncestor.root.right.right = new TreeNode(8);
        LowestCommonAncestor.root.left.right.left = new TreeNode(7);
        LowestCommonAncestor.root.left.right.right = new TreeNode(4);
        LowestCommonAncestor.p = new TreeNode(5);
        LowestCommonAncestor.q = new TreeNode(4);
    }

    public static void main(String[] args) {
        LowestCommonAncestor ancestor = new LowestCommonAncestor();
        System.out.println(ancestor.lowestCommonAncestor(
                LowestCommonAncestor.root,
                LowestCommonAncestor.p,
                LowestCommonAncestor.q
        ).val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p == q) {
            return p;
        }
        return dp(root, p, q).node;
    }

    private NodeGroup dp(TreeNode node, TreeNode p, TreeNode q) {
        NodeGroup nodeGroup = new NodeGroup(node, 0);;
        if (node == null) {
            return nodeGroup;
        }
        if (node.val == p.val) {
            nodeGroup.add(1);
        } else if (node.val == q.val) {
            nodeGroup.add(1);
        }
        NodeGroup left = dp(node.left, p, q);
        if (left.count == 2) {
            return left;
        } else if (left.count == 1) {
            nodeGroup.add(1);
            if (nodeGroup.count == 2) {
                return nodeGroup;
            }
        }
        NodeGroup right = dp(node.right, p, q);
        if (right.count == 2) {
            return right;
        } else if (right.count == 1) {
            nodeGroup.add(1);
            if (nodeGroup.count == 2) {
                return nodeGroup;
            }
        }
        return nodeGroup;
    }

    public class NodeGroup {
        TreeNode node;
        int count;

        public NodeGroup(TreeNode node, int count) {
            this.node = node;
            this.count = count;
        }

        public NodeGroup add(int num) {
            this.count += num;
            return this;
        }
    }
}