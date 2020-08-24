package com.zzp.learn.walklown.algorithm;

import com.zzp.learn.walklown.algorithm.base.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“
 * 房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * <p>
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 *      3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * @author walklown
 * @date 2020/8/5 21:36
 */
public class Rob3 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node6;

        System.out.println(rob(root));
    }

    public static int rob(TreeNode root) {
        return rob(root, false);
    }

    private static Map<TreeNode, Integer> SKIP_CACHE = new HashMap<>();

    private static Map<TreeNode, Integer> N_SKIP_CACHE = new HashMap<>();

    private static int rob(TreeNode node, boolean skip) {
        if (node == null) {
            return 0;
        }
        if (!skip) {
            Integer cacheValue = N_SKIP_CACHE.get(node);
            if (cacheValue != null) {
                return cacheValue.intValue();
            }
            int rise = rob(node.left, true) + rob(node.right, true) + node.val;
            int rise1 = rob(node.left, false) + rob(node.right, false);
            int result = Math.max(rise, rise1);
            N_SKIP_CACHE.put(node, result);
            return result;
        } else {
            Integer cacheValue = SKIP_CACHE.get(node);
            if (cacheValue != null) {
                return cacheValue.intValue();
            }
            int result = rob(node.left, false) + rob(node.right, false);
            SKIP_CACHE.put(node, result);
            return result;
        }
    }
}