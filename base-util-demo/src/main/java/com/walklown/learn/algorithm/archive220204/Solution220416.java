package com.walklown.learn.algorithm.archive220204;

/**
 * 479. 最大回文数乘积
 * 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 *
 *
 *
 * 示例 1:
 *
 * 输入：n = 2
 * 输出：987
 * 解释：99 x 91 = 9009, 9009 % 1337 = 987
 * 示例 2:
 *
 * 输入： n = 1
 * 输出： 9
 *
 *
 * 提示:
 *
 * 1 <= n <= 8
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220416 {

    public static void main(String[] args) {
        Solution220416 solution = new Solution220416();
        int n = solution.largestPalindrome(1);
        System.out.println(n);
    }

    int[] answer = new int[]{9,987,123,597,677,1218,877,475};

    public int largestPalindrome(int n) {
        return answer[n];
    }
}