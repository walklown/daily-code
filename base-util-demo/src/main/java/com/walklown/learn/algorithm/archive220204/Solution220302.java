package com.walklown.learn.algorithm.archive220204;

/**
 * 564. 寻找最近的回文数
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 *
 * “最近的”定义为两个整数差的绝对值最小。
 *
 *
 *
 * 示例 1:
 *
 * 输入: n = "123"
 * 输出: "121"
 * 示例 2:
 *
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 *
 *
 * 提示:
 *
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 1018 - 1] 范围内的整数
 *
 * TODO 抽象，冗余存储
 *
 * @author Walklown
 * @date 2022-02-17
 */
class Solution220302 {

    public static void main(String[] args) {
        Solution220302 solution = new Solution220302();
        System.out.println(solution.nearestPalindromic("10"));
    }

    public String nearestPalindromic(String n) {
        if ("0".equals(n)) {
            return "1";
        }
        if (n.length() == 1) {
            return String.valueOf(Integer.parseInt(n) - 1);
        }
        // 偶数位
        char[] chars = n.toCharArray();
        if (n.length() % 2 == 0) {
            int offset = n.length() / 2 - 1;
            int tail = Integer.parseInt(n.substring(offset));
            long number = Long.parseLong(n);
            char[] back = new char[offset + 2];
            // 基础数
            for (int i = 0; i <= offset; i++) {
                back[offset + 1 - i] = chars[i];
            }
            back[0] = back[1];
            int baseBack = Integer.parseInt(String.valueOf(back));
            if (baseBack > tail) {
                // 较大，计算更小的
                if (back[0] > '1') {
                    int change = 11;
                    for (int i = 0; i < offset; i++) {
                        change *= 10;
                    }
                    if (baseBack - tail > change / 2) {
                        return String.valueOf(number - tail + baseBack - change);
                    } else {
                        return String.valueOf(number - tail + baseBack);
                    }
                }
                int change = 1;
                for (int i = 0; i < offset + 1; i++) {
                    change *= 10;
                }
                char[] changeChars = String.valueOf(number - change).toCharArray();
                for (int i = 0; i <= offset; i++) {
                    back[offset + 1 - i] = changeChars[i];
                }
                back[0] = back[1];
                int minBack = Integer.parseInt(String.valueOf(back));
                if (tail - minBack > baseBack - tail) {
                    return String.valueOf(number - tail + baseBack);
                } else {
                    return String.valueOf(number - tail + minBack);
                }
            } else if (baseBack == number) {
                return n;
            } else {
                //  if (baseBack < number)
                // 较小，计算更大的
                if (back[0] < '9') {
                    int change = 11;
                    for (int i = 0; i < offset; i++) {
                        change *= 10;
                    }
                    if (tail - baseBack > change / 2) {
                        return String.valueOf(number - tail + baseBack + change);
                    } else {
                        return String.valueOf(number - tail + baseBack);
                    }
                }
                int change = 1;
                for (int i = 0; i < offset + 1; i++) {
                    change *= 10;
                }
                char[] changeChars = String.valueOf(number + change).toCharArray();
                for (int i = 0; i <= offset; i++) {
                    back[offset + 1 - i] = changeChars[i];
                }
                back[0] = back[1];
                int maxBack = Integer.parseInt(String.valueOf(back));
                if (tail - baseBack < maxBack - tail) {
                    return String.valueOf(number - tail + baseBack);
                } else {
                    for (int i = offset; i < changeChars.length; i++) {
                        changeChars[i] = back[i - offset];
                    }
                    return String.valueOf(Long.parseLong(String.valueOf(changeChars)));
                }
            }
        } else {
            // 奇数位
            int offset = n.length() / 2;
            int tail = Integer.parseInt(n.substring(offset));
            long number = Long.parseLong(n);
            char[] back = new char[offset + 1];
            // 基础数
            for (int i = 0; i <= offset; i++) {
                back[offset - i] = chars[i];
            }
            int baseBack = Integer.parseInt(String.valueOf(back));
            if (baseBack > tail) {
                // 较大，计算更小的
                if (back[0] > '1') {
                    int change = 1;
                    for (int i = 0; i < offset; i++) {
                        change *= 10;
                    }
                    if (baseBack - tail > change / 2) {
                        return String.valueOf(number - tail + baseBack - change);
                    } else {
                        return String.valueOf(number - tail + baseBack);
                    }
                }
                int change = 1;
                for (int i = 0; i < offset + 1; i++) {
                    change *= 10;
                }
                char[] changeChars = String.valueOf(number - change).toCharArray();
                for (int i = 0; i <= offset; i++) {
                    back[offset - i] = changeChars[i];
                }
                int minBack = Integer.parseInt(String.valueOf(back));
                if (tail - minBack > baseBack - tail) {
                    return String.valueOf(number - tail + baseBack);
                } else {
                    return String.valueOf(number - tail + minBack);
                }
            } else if (baseBack == number) {
                return n;
            } else {
                //  if (baseBack < number)
                // 较小，计算更大的
                if (back[0] < '9') {
                    int change = 1;
                    for (int i = 0; i < offset; i++) {
                        change *= 10;
                    }
                    if (tail - baseBack > change / 2) {
                        return String.valueOf(number - tail + baseBack + change);
                    } else {
                        return String.valueOf(number - tail + baseBack);
                    }
                }
                int change = 1;
                for (int i = 0; i < offset + 1; i++) {
                    change *= 10;
                }
                char[] changeChars = String.valueOf(number + change).toCharArray();
                for (int i = 0; i <= offset; i++) {
                    back[offset - i] = changeChars[i];
                }
                int maxBack = Integer.parseInt(String.valueOf(back));
                if (tail - baseBack < maxBack - tail) {
                    return String.valueOf(number - tail + baseBack);
                } else {
                    for (int i = offset; i < changeChars.length; i++) {
                        changeChars[i] = back[i - offset];
                    }
                    return String.valueOf(Long.parseLong(String.valueOf(changeChars)));
                }
            }
        }
    }

    private void getBack() {

    }
}
