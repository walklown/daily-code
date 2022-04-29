package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。
 * <p>
 * 重复出现的子串要计算它们出现的次数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入: "00110011"
 * 输出: 6
 * 解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
 * <p>
 * 请注意，一些重复出现的子串要计算它们出现的次数。
 * <p>
 * 另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
 * 示例 2 :
 * <p>
 * 输入: "10101"
 * 输出: 4
 * 解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
 * 注意：
 * <p>
 * s.length 在1到50,000之间。
 * s 只包含“0”或“1”字符。
 *
 * @author walklown
 * @date 2020/8/10 23:36
 */
class CountBinarySubstrings {

    public static void main(String[] args) {
        CountBinarySubstrings countBinarySubstrings = new CountBinarySubstrings();
        System.out.println(countBinarySubstrings.countBinarySubstrings("00110011"));
    }

    public int countBinarySubstrings1(String s) {
        int n = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            // 不等的位置是对称点
            if (s.charAt(i) != s.charAt(i + 1)) {
                n++;
                // 探索所有包含当前对称点的对称字符串
                for (int x = i - 1, y = i + 2; x >= 0 & y < s.length(); x--, y++) {
                    if (s.charAt(x) == s.charAt(x + 1)
                            && s.charAt(y) == s.charAt(y - 1)) {
                        n ++;
                    } else {
                        break;
                    }
                }
            }
        }
        return n;
    }

    public int countBinarySubstrings(String s) {
        int n = 0;
        int last = 0;
        int current = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            // 不等的位置是对称点
            if (s.charAt(i) == s.charAt(i + 1)) {
                current ++;
            } else {
                n += Math.min(last, current);
                last = current;
                current = 1;
            }
        }
        n += Math.min(last, current);
        return n;
    }
}