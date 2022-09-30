package com.zzp.learn.walklown.algorithm;

import java.util.LinkedList;

/**
 * 面试题 01.09. 字符串轮转
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成（比如，waterbottle是erbottlewat旋转后的字符串）。
 *
 * 示例1:
 *
 *  输入：s1 = "waterbottle", s2 = "erbottlewat"
 *  输出：True
 * 示例2:
 *
 *  输入：s1 = "aa", s2 = "aba"
 *  输出：False
 * 提示：
 *
 * 字符串长度在[0, 100000]范围内。
 * 说明:
 *
 * 你能只调用一次检查子串的方法吗？
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220929 {

    public static void main(String[] args) {
        Solution220929 solution = new Solution220929();
        System.out.println(solution.isFlipedString("waterbottle", "erbottlewat"));
    }

    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.length() == 0) {
            return true;
        }
        for (int i = 0; i < s1.length(); i++) {
            if (s1.substring(i).equals(s2.substring(0, s2.length() - i))) {
                if (i == 0) {
                    return true;
                }
                if (s1.substring(0, i).equals(s2.substring(s2.length() - i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
