package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 给你一个字符串 s ，请你返回满足以下条件的最长子字符串的长度：每个元音字母，即 'a'，'e'，'i'，'o'，'u' ，在子字符串中都恰好出现了偶数次。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "eleetminicoworoep"
 * 输出：13
 * 解释：最长子字符串是 "leetminicowor" ，它包含 e，i，o 各 2 个，以及 0 个 a，u 。
 * 示例 2：
 * <p>
 * 输入：s = "leetcodeisgreat"
 * 输出：5
 * 解释：最长子字符串是 "leetc" ，其中包含 2 个 e 。
 * 示例 3：
 * <p>
 * 输入：s = "bcbcbc"
 * 输出：6
 * 解释：这个示例中，字符串 "bcbcbc" 本身就是最长的，因为所有的元音 a，e，i，o，u 都出现了 0 次。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 5 x 10^5
 * s 只包含小写英文字母。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shoujing
 * @date 2020/5/20 22:35
 */
class FindTheLongestSubstring {

    public static void main(String[] args) {
        System.out.println(1 ^ 1);
    }

    public int findTheLongestSubstring(String s) {
        int a = 0, e = 0, i = 0, o = 0, u = 0;
        int length = 0;
        int x = 0;
        for (char c : s.toCharArray()) {
            x ++;
            switch (c) {
                case 'a':
                    a ^= 1;
                    break;
                case 'e':
                    e ^= 1;
                    break;
                case 'i':
                    i ^= 1;
                    break;
                case 'o':
                    o ^= 1;
                    break;
                case 'u':
                    u ^= 1;
                    break;
                default:
                    break;
            }
            if (a == 0
                    && e == 0
                    && i == 0
                    && o == 0
                    && u == 0 ) {
                length += x;
                x = 0;
            }
        }
        return 0;
    }
}