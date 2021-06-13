package com.zzp.learn.walklown.algorithm;

/**
 * 1208. 尽可能使字符串相等
 * 给你两个长度相同的字符串，s 和 t。
 * 将 s 中的第 i 个字符变到 t 中的第 i 个字符需要 |s[i] - t[i]| 的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 * 用于变更字符串的最大预算是 maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 */
public class Solution210205 {

    public static void main(String[] args) {
        Solution210205 solution210205 = new Solution210205();
        System.out.println(solution210205.equalSubstring("abcd", "bcdf", 3));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        int[] minus = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) > t.charAt(i)) {
                minus[i] = s.charAt(i) - t.charAt(i);
            } else {
                minus[i] = t.charAt(i) - s.charAt(i);
            }
        }
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            int length = cost(minus, maxCost, i);
            if (maxLength < length) {
                maxLength = length;
            }
        }
        return maxLength;
    }

    private int cost(int[] minus, int maxCost, int n) {
        int lik = maxCost;
        for (int i = n; i < minus.length; i++) {
            lik = lik - minus[i];
            if (lik < 0) {
                return i - n;
            }
        }
        return minus.length - n;
    }
}
