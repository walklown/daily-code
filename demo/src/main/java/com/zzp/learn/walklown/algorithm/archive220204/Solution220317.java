package com.zzp.learn.walklown.algorithm.archive220204;

import java.util.Arrays;

/**
 * 720. 词典中最长的单词
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
 * <p>
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 * 示例 2：
 * <p>
 * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * 所有输入的字符串 words[i] 都只包含小写字母。
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220317 {

    public static void main(String[] args) {
        Solution220317 solution = new Solution220317();
        System.out.println(solution.longestWord(
                new String[] {"rac", "rs", "ra", "on", "r", "otif", "o", "onpdu", "rsf", "rs", "ot", "oti", "racy", "onpd"}));
    }

    private static final String EMPTY = "";

    public String longestWord(String[] words) {
        Arrays.sort(words);
        String[] lastWord = new String[words.length + 1];
        lastWord[0] = EMPTY;
        String result = EMPTY;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() == 1) {
                lastWord[1] = words[i];
                if (lastWord[1].length() > result.length()) {
                    result = words[i];
                }
            } else if (words[i].length() > words.length) {
                continue;
            } else if (words[i].substring(0, words[i].length() - 1).equals(lastWord[words[i].length() - 1])) {
                int offset = words[i].length();
                lastWord[offset] = words[i];
                if (lastWord[offset].length() > result.length()) {
                    result = words[i];
                }
            }
        }
        return result;
    }

//    private static final String EMPTY = "";
//
//    private String[] words;
//
//    private String[] last;
//
//    private int nextLength;
//
//    public String longestWord(String[] words) {
//        this.words = words;
//        nextLength = words.length;
//        last = new String[words.length];
//        last[0] = EMPTY;
//        String[] results = last;
//        for (int i = 1; i <= words.length; i++) {
//            last = next(last, i);
//            if (last[0] == null) {
//                break;
//            } else {
//                results = last;
//            }
//        }
//        if (results[0] == null) {
//            return EMPTY;
//        }
//        return results[0];
//    }
//
//    private String[] next(String[] last, int length) {
//        String[] next = new String[nextLength];
//        int i = 0;
//        for (String word : words) {
//            if (word.length() == length) {
//                for (String s : last) {
//                    if (s == null) {
//                        break;
//                    }
//                    if (s.equals(word.substring(0, word.length() - 1))) {
//                        next[i] = word;
//                        i++;
//                    }
//                }
//            }
//        }
//        return next;
//    }
}