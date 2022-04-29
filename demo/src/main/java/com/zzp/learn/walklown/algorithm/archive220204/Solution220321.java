package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入: root = [5,3,6,2,4,null,7], k = 9
 * 输出: true
 * 示例 2：
 * <p>
 * <p>
 * 输入: root = [5,3,6,2,4,null,7], k = 28
 * 输出: false
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是  [1, 104].
 * -104 <= Node.val <= 104
 * root 为二叉搜索树
 * -105 <= k <= 105
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220321 {

    public static void main(String[] args) {
        Solution220321 solution = new Solution220321();
//        int far = solution.findTarget();
//        System.out.println(far);
    }

    private TreeNode root;

    private int k;

    public boolean findTarget(TreeNode root, int k) {
        this.root = root;
        this.k = k;
        if (nextBiggerThanHalf(root)) {
            return true;
        }
        return false;
    }

    private boolean nextBiggerThanHalf(TreeNode node) {
        if (node == null) {
            return false;
        }
        int doubleValue = node.val * 2;
        if ((doubleValue > 0)  ) {
            if (node.val < k) {
                if (find(root, k - root.val)) {
                    return true;
                }
                if (nextBiggerThanHalf(node.left)) {
                    return true;
                }
                if (nextBiggerThanHalf(node.right)) {
                    return true;
                }
            } else {
                if (nextBiggerThanHalf(node.left)) {
                    return true;
                }
            }
        } else {
            if (nextBiggerThanHalf(node.right)) {
                return true;
            }
        }
        return false;
    }

    private boolean find(TreeNode node, int least) {
        if (node == null) {
            return false;
        }
        if (node.val == least) {
            return true;
        } else if (node.val > least) {
            if (find(node.left, least)) {
                return true;
            }
        } else {
            if (find(node.right, least)) {
                return true;
            }
        }
        return false;
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