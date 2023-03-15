package com.walklown.learn.algorithm.archive220204;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例：
 * 输入：s = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * <p>
 * 进阶：
 * 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * @author walklown
 * @date 2020/6/28 23:21
 */
class MinSubArrayLen {

    public static void main(String[] args) {
        MinSubArrayLen minSubArrayLen = new MinSubArrayLen();
        int s = 7;
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(minSubArrayLen.minSubArrayLen(s, nums));
    }

    public int minSubArrayLen(int s, int[] nums) {
        int i = 0;
        int sum = 0;
        int minLength = 0;
        int length = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            if (sum >= s) {
                for (; ; ) {
                    length = j - i + 1;
                    if (minLength == 0 || minLength > length) {
                        minLength = length;
                    }
                    sum -= nums[i];
                    i++;
                    if (sum < s) {
                        break;
                    }
                }
            }
        }
        return minLength;
    }
}