package com.zzp.learn.walklown.algorithm;

/**
 * 917. 仅仅反转字母
 * 给你一个字符串 s ，根据下述规则反转字符串：
 * <p>
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 * <p>
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 * <p>
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *  
 * <p>
 * 提示
 * <p>
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-only-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220223 {

    public static void main(String[] args) {
        Solution220223 solution = new Solution220223();
        System.out.println(solution.reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        char value;
        int offset = chars.length - 1;
        for (int i = 0; i < chars.length; i++) {
            if (Character.isUpperCase(chars[i]) || Character.isLowerCase(chars[i])) {
                for (; offset > i; offset--) {
                    if (Character.isUpperCase(chars[offset]) || Character.isLowerCase(chars[offset])) {
                        value = chars[i];
                        chars[i] = chars[offset];
                        chars[offset] = value;
                        offset--;
                        break;
                    }
                }
            }
            if (i == offset) {
                break;
            }
        }
        return String.valueOf(chars);
    }
}
