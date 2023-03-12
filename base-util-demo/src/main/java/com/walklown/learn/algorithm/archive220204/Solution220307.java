package com.walklown.learn.algorithm.archive220204;

/**
 * 504. 七进制数
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * <p>
 * 输入: num = -7
 * 输出: "-10"
 * <p>
 * <p>
 * 提示：
 * <p>
 * -107 <= num <= 107
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220307 {

    public static void main(String[] args) {
        Solution220307 solution = new Solution220307();
        System.out.println(solution.convertToBase7(100));
    }

    public String convertToBase7(int num) {
        int mid;
        if (num < 0) {
            mid = -num;
        } else {
            mid = num;
        }
        StringBuilder builder = new StringBuilder();
        do {
            builder.insert(0, mid % 7);
            mid = mid / 7;
        } while (mid > 0);
        if (num < 0) {
            builder.insert(0, "-");
        }
        return builder.toString();
    }
}
