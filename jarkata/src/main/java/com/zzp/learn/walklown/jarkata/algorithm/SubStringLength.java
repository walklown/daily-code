package com.zzp.learn.walklown.jarkata.algorithm;

import java.util.HashMap;

/**
 * 无重复字符的最长子串
 *
 * @author shoujing
 * @date 2020/5/2 17:39
 */
class SubStringLength {

    public static void main(String[] args) {
        SubStringLength subStringLength = new SubStringLength();
        System.out.println(subStringLength.lengthOfLongestSubstring("au"));
    }

    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        HashMap<Character, Integer> hashSet = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int length0 = 1;
            hashSet.put(s.charAt(i), i);
            Integer fromValue = null;
            for (int j = i + 1; j < s.length(); j++) {
                fromValue = hashSet.put(s.charAt(j), j);
                if (fromValue != null) {
                    int length = j - i;
                    if (length0 < length) {
                        length0 = length;
                    }
                    i = fromValue;
                    break;
                }
                if (j == s.length() - 1) {
                    int length = s.length() - i;
                    if (length0 < length) {
                        length0 = length;
                    }
                }
            }
            if (result < length0) {
                result = length0;
            }
            if (result >= s.length() - i) {
                break;
            }
            hashSet = new HashMap<>();
        }
        return result;
    }
}