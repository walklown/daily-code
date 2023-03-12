package com.walklown.learn.algorithm;

/**
 * 1781. 所有子字符串美丽值之和
 * 一个字符串的 美丽值 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。
 * <p>
 * 比方说，"abaacc" 的美丽值为 3 - 1 = 2 。
 * 给你一个字符串 s ，请你返回它所有子字符串的 美丽值 之和。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "aabcb"
 * 输出：5
 * 解释：美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。
 * 示例 2：
 * <p>
 * 输入：s = "aabcbaa"
 * 输出：17
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 */
class Solution221212 {

    public static void main(String[] args) {
        Solution221212 solution = new Solution221212();
        System.out.println(solution.beautySum("aabcbaa"));
    }

    public int beautySum(String s) {
        if (s.length() < 3) {
            return 0;
        }
        int num = 0;
        for (int l = 0; l <= s.length() - 3; l++) {
            int[] cnt = new int[26];
            cnt[s.charAt(l) - 'a']++;
            cnt[s.charAt(l + 1) - 'a']++;
            int max = Math.max(cnt[s.charAt(l) - 'a'], cnt[s.charAt(l + 1) - 'a']);
            for (int r = l + 2; r < s.length(); r++) {
                cnt[s.charAt(r) - 'a']++;
                max = Math.max(cnt[s.charAt(r) - 'a'], max);
                int min = s.length();
                for (int no = 0; no < 26; no++) {
                    if (cnt[no] == 0) {
                        continue;
                    }
                    if (min > cnt[no]) {
                        min = cnt[no];
                    }
                }
                num += max - min;
            }
        }
        return num;
    }
}
