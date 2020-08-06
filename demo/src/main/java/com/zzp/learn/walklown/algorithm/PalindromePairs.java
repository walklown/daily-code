package com.zzp.learn.walklown.algorithm;

import java.util.*;

/**
 * 回文对
 * <p>
 * 给定一组 互不相同 的单词， 找出所有不同的索引对(i, j)，使得列表中的两个单词，words[i] + words[j]，可拼接成回文串。
 * <p>
 * 示例 1：
 * <p>
 * 输入：["abcd","dcba","lls","s","sssll"]
 * 输出：[[0,1],[1,0],[3,2],[2,4]]
 * 解释：可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2：
 * <p>
 * 输入：["bat","tab","cat"]
 * 输出：[[0,1],[1,0]]
 * 解释：可拼接成的回文串为 ["battab","tabbat"]
 *
 * @author walklown
 * @date 2020/8/6 22:40
 */
class PalindromePairs {

    public static void main(String[] args) {
        PalindromePairs palindromePairs = new PalindromePairs();
        System.out.println(palindromePairs.palindromePairs(new String[]{"a", "abc", "aba", ""}));
    }

    private final Map<CacheKey, Boolean> CACHE = new HashMap<>();

    class CacheKey {

        Integer strIndex;

        Integer charIndex;

        public CacheKey(Integer strIndex, Integer charIndex) {
            this.strIndex = strIndex;
            this.charIndex = charIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            CacheKey cacheKey = (CacheKey) o;
            return Objects.equals(strIndex, cacheKey.strIndex) &&
                    Objects.equals(charIndex, cacheKey.charIndex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(strIndex, charIndex);
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> results = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                int length = words[i].length() + words[j].length();
                boolean ifTrue = true;
                if (words[i].length() >= words[j].length()) {
                    for (int n = 0; n < words[j].length(); n++) {
                        if (words[i].charAt(n) != words[j].charAt(words[j].length() - n - 1)) {
                            ifTrue = false;
                            break;
                        }
                    }
                    if (ifTrue) {
                        CacheKey cacheKey = new CacheKey(i, length);
                        Boolean cacheIf = CACHE.get(cacheKey);
                        if (cacheIf != null) {
                            ifTrue = cacheIf;
                        } else {
                            for (int n = words[j].length(); n < words[i].length(); n++) {
                                if (words[i].charAt(n) != words[i].charAt(length - n - 1)) {
                                    ifTrue = false;
                                    break;
                                }
                            }
                            CACHE.put(cacheKey, ifTrue);
                        }
                    }
                } else {
                    for (int n = 0; n < words[i].length(); n++) {
                        if (words[i].charAt(n) != words[j].charAt(words[j].length() - n - 1)) {
                            ifTrue = false;
                            break;
                        }
                    }
                    if (ifTrue) {
                        CacheKey cacheKey = new CacheKey(j, words[j].length() - words[i].length());
                        Boolean cacheIf = CACHE.get(cacheKey);
                        if (cacheIf != null) {
                            ifTrue = cacheIf;
                        } else {
                            for (int n = words[i].length(); n < words[j].length(); n++) {
                                if (words[j].charAt(n - words[i].length()) != words[j].charAt(words[j].length() - n - 1)) {
                                    ifTrue = false;
                                    break;
                                }
                            }
                            CACHE.put(cacheKey, ifTrue);
                        }
                    }
                }
                if (ifTrue) {
                    List<Integer> result = new LinkedList<>();
                    result.add(i);
                    result.add(j);
                    results.add(result);
                }
            }
        }
        return results;
    }
}