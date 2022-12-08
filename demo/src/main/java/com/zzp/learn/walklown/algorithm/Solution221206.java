package com.zzp.learn.walklown.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * 1805. 字符串中不同整数的数目
 * 给你一个字符串 word ，该字符串由数字和小写英文字母组成。
 * <p>
 * 请你用空格替换每个不是数字的字符。例如，"a123bc34d8ef34" 将会变成 " 123  34 8  34" 。注意，剩下的这些整数为（相邻彼此至少有一个空格隔开）："123"、"34"、"8" 和 "34" 。
 * <p>
 * 返回对 word 完成替换后形成的 不同 整数的数目。
 * <p>
 * 只有当两个整数的 不含前导零 的十进制表示不同， 才认为这两个整数也不同。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：word = "a123bc34d8ef34"
 * 输出：3
 * 解释：不同的整数有 "123"、"34" 和 "8" 。注意，"34" 只计数一次。
 * 示例 2：
 * <p>
 * 输入：word = "leet1234code234"
 * 输出：2
 * 示例 3：
 * <p>
 * 输入：word = "a1b01c001"
 * 输出：1
 * 解释："1"、"01" 和 "001" 视为同一个整数的十进制表示，因为在比较十进制值时会忽略前导零的存在。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word.length <= 1000
 * word 由数字和小写英文字母组成
 */
class Solution221206 {

    public static void main(String[] args) {
        Solution221206 solution = new Solution221206();
        System.out.println(solution.numDifferentIntegers("0a0"));
    }

    public int numDifferentIntegers(String word) {
        StringBuilder builder = new StringBuilder();
        Set<String> strSet = new HashSet<>();
        for (int offset = 0; offset < word.length(); offset++) {
            char a = word.charAt(offset);
            if (a > '0' && a <= '9') {
                // 是数字
                if (builder.length() > 0 && builder.charAt(0) == '0') {
                    builder.deleteCharAt(0);
                }
                builder.append(a);
            } else if (a == '0') {
                if (builder.length() > 0) {
                    if (builder.charAt(0) != '0') {
                        builder.append(a);
                    }
                } else {
                    builder.append(a);
                }
            } else if (builder.length() > 0) {
                String s = builder.toString();
                strSet.add(s);
                builder = new StringBuilder();
            }
        }
        if (builder.length() > 0) {
            String s = builder.toString();
            strSet.add(s);
        }
        return strSet.size();
    }
}
