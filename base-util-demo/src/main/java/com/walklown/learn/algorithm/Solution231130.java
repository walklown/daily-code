package com.walklown.learn.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1657. 确定两个字符串是否接近
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 * <p>
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：word1 = "abc", word2 = "bca"
 * 输出：true
 * 解释：2 次操作从 word1 获得 word2 。
 * 执行操作 1："abc" -> "acb"
 * 执行操作 1："acb" -> "bca"
 * 示例 2：
 * <p>
 * 输入：word1 = "a", word2 = "aa"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 * 示例 3：
 * <p>
 * 输入：word1 = "cabbba", word2 = "abbccc"
 * 输出：true
 * 解释：3 次操作从 word1 获得 word2 。
 * 执行操作 1："cabbba" -> "caabbb"
 * 执行操作 2："caabbb" -> "baaccc"
 * 执行操作 2："baaccc" -> "abbccc"
 * 示例 4：
 * <p>
 * 输入：word1 = "cabbba", word2 = "aabbss"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= word1.length, word2.length <= 105
 * word1 和 word2 仅包含小写英文字母
 *
 * @author Walklown
 */
public class Solution231130 {

    public static void main(String[] args) {
        Solution231130 solution = new Solution231130();
        System.out.println(solution.closeStrings("svotbsgqiqmeqjwdqqtkucrzqphqxqtqqlyfan", "aapyhufaaaalkqsvtjnaaoewxkrgsbsazadmci"));
    }

    public boolean closeStrings(String word1, String word2) {
        int size;
        if ((size = word1.length()) != word2.length()) {
            return false;
        }
        int[] a = new int[26];
        int[] b = new int[26];
        for (int i = 0; i < size; i++) {
            a[word1.charAt(i) - 'a']++;
            b[word2.charAt(i) - 'a']++;
        }
        for (int i = 1; i < 26; i++) {
            if (a[i] == 0 || b[i] == 0) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
        }
        for (int j = 0; j < 25; j++) {
            // a排序
            for (int i = 0; i < 26 - j - 1; i++) {
                if (a[i] < a[i + 1]) {
                    int mind = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = mind;
                }
                if (b[i] < b[i + 1]) {
                    int mind = b[i];
                    b[i] = b[i + 1];
                    b[i + 1] = mind;
                }
            }
        }
        for (int i = 0; i < 26; i++) {
            if (a[i] != b[i]) {
                return false;
            }
            if (a[i] == 0) {
                return true;
            }
        }
        return true;
    }
}
