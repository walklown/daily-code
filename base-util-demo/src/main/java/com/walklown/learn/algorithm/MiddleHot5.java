package com.walklown.learn.algorithm;

/**
 * 5. 最长回文子串
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot5 {

    public static void main(String[] args) {
        MiddleHot5 middleHot = new MiddleHot5();
        System.out.println(middleHot.longestPalindrome("abba"));
    }

    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int max = 1;
        String maxString = String.valueOf(chars[0]);
        if (chars[0] == chars[1]) {
            max = 2;
            maxString = String.valueOf(chars, 0, 2);
        }
        for (int i = 1; i < s.length() - (max + 1) / 2; i++) {
            int thisMax = 1;
            int left;
            int right;
            for (left = i - 1, right = i + 1; left >= 0 && right < s.length(); left--, right++) {
                if (chars[left] == chars[right]) {
                    thisMax += 2;
                } else {
                    break;
                }
            }
            if (thisMax > max) {
                max = thisMax;
                maxString = String.valueOf(chars, left + 1, thisMax);
            }
            int thisMax1 = 0;
            for (left = i, right = i + 1; left >= 0 && right < s.length(); left--, right++) {
                if (chars[left] == chars[right]) {
                    thisMax1 += 2;
                } else {
                    break;
                }
            }
            if (thisMax1 > max) {
                max = thisMax1;
                maxString = String.valueOf(chars, left + 1, thisMax1);
            }
        }
        return maxString;
    }

}
