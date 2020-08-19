package com.zzp.learn.walklown.algorithm;


/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
class Solution200819 {

    public static void main(String[] args) {
        Solution200819 solution = new Solution200819();
        System.out.println(solution.countSubstrings("fdsklf"));
    }

    public int countSubstrings(String s) {
        int size = 0;
        for (int i = 0; i < s.length(); i++) {
            // 移动中心
            // 先算上自己
            size++;
            if (i < s.length() - 1) {
                size += huiwen(s, i, i + 1);
                if (i > 0) {
                    size += huiwen(s, i - 1, i + 1);
                }
            }
        }
        return size;
    }

    private int huiwen(String s, int left, int right) {
        int size = 0;
        if (s.charAt(left) != s.charAt(right)) {
            return size;
        }
        size++;
        if (left > 0 && right < s.length() - 1) {
            return size + huiwen(s, left - 1, right + 1);
        } else {
            return size;
        }
    }
}