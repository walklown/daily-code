package com.zzp.learn.walklown.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 2044. 统计按位或能得到最大值的子集数目
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 * <p>
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 * <p>
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,1]
 * 输出：2
 * 解释：子集按位或能得到的最大值是 3 。有 2 个子集按位或可以得到 3 ：
 * - [3]
 * - [3,1]
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2]
 * 输出：7
 * 解释：[2,2,2] 的所有非空子集的按位或都可以得到 2 。总共有 23 - 1 = 7 个子集。
 * 示例 3：
 * <p>
 * 输入：nums = [3,2,1,5]
 * 输出：6
 * 解释：子集按位或可能的最大值是 7 。有 6 个子集按位或可以得到 7 ：
 * - [3,5]
 * - [3,1,5]
 * - [3,2,5]
 * - [3,2,1,5]
 * - [2,5]
 * - [2,1,5]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 16
 * 1 <= nums[i] <= 105 <10000000
 *
 * TODO 结果是暴力破解
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220315 {

    public static void main(String[] args) {
        Solution220315 solution = new Solution220315();
        System.out.println(solution.countMaxOrSubsets(new int[] {1, 2}));
    }

    public int countMaxOrSubsets(int[] nums) {
        // 全集按位或一定是最大的
//        int max = 0;
//        for (int i = 0; i < nums.length - 1; i++) {
//            max |= nums[i];
//        }
        // 计算位数
        int[] bits1 = new int[nums.length];
        int num = 1;
        int bitNum = 1;
        while (num > 0) {
            int could = 0;
            int hasCould = 0;
            for (int i = 0; i < nums.length - 1; i++) {
//                int num = nums[i];
                if (nums[i] % 2 == 1) {
                    could++;
                    if (bits1[i] != 0) {
                        hasCould++;
                    }
                    bits1[i]++;
                }
                nums[i] = nums[i] / 2;
            }
            if (could > 0) {
                num *= (2 * could - 1);
            }
            if (hasCould > 0) {
                num -= 2 * hasCould * (num - 1);
            }
            bitNum++;
        }
        return num;
    }
}