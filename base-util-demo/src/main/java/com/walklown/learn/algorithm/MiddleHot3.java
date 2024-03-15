package com.walklown.learn.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 3. 无重复字符的最长子串
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot3 {

    public static void main(String[] args) {
        MiddleHot3 middleHot = new MiddleHot3();
        System.out.println(middleHot.lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        Map<Character, Integer> memory = new HashMap<>();
        int maxLength = 1;
        int length = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char n = s.charAt(i);
            Integer offset = memory.get(n);
            if (offset != null && offset >= start) {
                // has
                if (length > maxLength) {
                    maxLength = length;
                }
                length = i - offset;
                memory.put(n, i);
                start = offset;
            } else {
                memory.put(n, i);
                length++;
            }
        }
        if (length > maxLength) {
            maxLength = length;
        }
        return maxLength;
    }
}
