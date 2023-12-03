package com.walklown.learn.algorithm;

/**
 * 2609. 最长平衡子字符串
 * 提示
 * 简单
 * 68
 * 相关企业
 * 给你一个仅由 0 和 1 组成的二进制字符串 s 。
 * 如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字符串。
 * 返回  s 中最长的平衡子字符串长度。
 * 子字符串是字符串中的一个连续字符序列。
 * 提示：
 * 1 <= s.length <= 50
 * '0' <= s[i] <= '1'
 *
 * @author Walklown
 */
public class Solution231108 {

    public static void main(String[] args) {
        Solution231108 solution = new Solution231108();
        System.out.println(solution.findTheLongestBalancedSubstring("01000111"));
    }

    public int findTheLongestBalancedSubstring(String s) {
        int maxLength = 0;
        int zeroLength = 0;
        int oneLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            if (a == '0') {
                if (oneLength == 0) {
                    zeroLength++;
                } else {
                    if (oneLength > maxLength) {
                        maxLength = oneLength;
                    }
                    zeroLength = 1;
                    oneLength = 0;
                }
            } else if (a == '1') {
                if (zeroLength > oneLength) {
                    oneLength++;
                    if (zeroLength == oneLength) {
                        if (oneLength > maxLength) {
                            maxLength = oneLength;
                        }
                        zeroLength = 0;
                        oneLength = 0;
                    }
                }
            }
        }
        if (oneLength > maxLength) {
            maxLength = oneLength;
        }
        return maxLength * 2;
    }
}
