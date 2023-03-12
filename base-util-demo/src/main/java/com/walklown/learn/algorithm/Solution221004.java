package com.walklown.learn.algorithm;

/**
 * 921. 使括号有效的最少添加
 * 只有满足下面几点之一，括号字符串才是有效的：
 *
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
 *
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "())"
 * 输出：1
 * 示例 2：
 *
 * 输入：s = "((("
 * 输出：3
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 只包含 '(' 和 ')' 字符。
 * 通过次数55,905提交次数76,608
 */
class Solution221004 {

    public static void main(String[] args) {
        Solution221004 solution = new Solution221004();
        System.out.println(solution.minAddToMakeValid("()))(("));
    }

    public int minAddToMakeValid(String s) {
        int i = 0;
        int current = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (current > 0) {
                    i += current;
                    current = 0;
                }
                current--;
            } else {
                current++;
            }
        }
        if (current < 0) {
            i -= current;
        } else {
            i += current;
        }
        return i;
    }
}
