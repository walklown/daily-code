package com.walklown.learn.algorithm.archive220204;

import java.util.HashSet;
import java.util.Set;

/**
 * 804. 唯一摩尔斯密码词
 * 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如:
 * <p>
 * 'a' 对应 ".-" ，
 * 'b' 对应 "-..." ，
 * 'c' 对应 "-.-." ，以此类推。
 * 为了方便，所有 26 个英文字母的摩尔斯密码表如下：
 * <p>
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * 给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。
 * <p>
 * 例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作 单词翻译 。
 * 对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: words = ["gin", "zen", "gig", "msg"]
 * 输出: 2
 * 解释:
 * 各单词翻译如下:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 * <p>
 * 共有 2 种不同翻译, "--...-." 和 "--...--.".
 * 示例 2：
 * <p>
 * 输入：words = ["a"]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 12
 * words[i] 由小写英文字母组成
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220410 {

    public static void main(String[] args) {
        Solution220410 solution = new Solution220410();
        int n = solution.uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"});
        System.out.println(n);
    }

    private static final String[] code =
            new String[] {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.",
                    "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
    public int uniqueMorseRepresentations(String[] words) {
        if (words.length == 1) {
            return 1;
        }
        Set<String> codeSet = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (char c : words[i].toCharArray()) {
                builder.append(code[c - 'a']);
            }
            codeSet.add(builder.toString());
        }
        return codeSet.size();
    }
}