package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 357. 统计各位数字都不同的数字个数
 * 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10n 。
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：91
 * 解释：答案应为除去 11、22、33、44、55、66、77、88、99 外，在 0 ≤ x < 100 范围内的所有数字。
 * 示例 2：
 *
 * 输入：n = 0
 * 输出：1
 *
 *
 * 提示：
 *
 * 0 <= n <= 8
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220411 {

    public static void main(String[] args) {
        Solution220411 solution = new Solution220411();
        int n = solution.countNumbersWithUniqueDigits(1);
        System.out.println(n);
    }

    public int countNumbersWithUniqueDigits(int n) {
        int dCount = 1;
        for (int i = 1; i <= n; i++) {
            int num = 9;
            int a = 9;
            for (int j = 2; j <= i; j++) {
                num *= a;
                a--;
            }
            dCount += num;
        }
        return dCount;
    }
}