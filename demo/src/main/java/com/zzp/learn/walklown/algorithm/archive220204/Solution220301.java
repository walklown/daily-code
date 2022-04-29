package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * 示例 3：
 * <p>
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 由英文字母（小写和大写）、',' 和 '.' 组成
 * 1 <= numRows <= 1000
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220301 {

    public static void main(String[] args) {
        Solution220301 solution = new Solution220301();
        System.out.println(solution.convert("PAYPALISHIRING", 2));
    }

    private int unit, numRows, loop, limit;

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        this.unit = numRows * 2 - 2;
        this.numRows = numRows;
        this.loop = s.length() / unit;
        this.limit = s.length() % unit;
        this.loop++;
        char[] chars = new char[loop * unit];
        for (int j = 0; j < unit; j++) {
            for (int i = 0; i < loop - 1; i++) {
                convert(s, j, i, chars);
            }
        }
        for (int j = 0; j < limit; j++) {
            convert(s, j, loop - 1, chars);
        }
        StringBuilder builder = new StringBuilder();
        for (char aChar : chars) {
            if (aChar == 0) {
                continue;
            }
            builder.append(aChar);
        }
        return builder.toString();
    }

    private void convert(String s, int unitOffset, int loopOffset, char[] chars) {
        char readChar = s.charAt(loopOffset * unit + unitOffset);
        if (unitOffset == 0) {
            chars[loopOffset] = readChar;
        } else if (unitOffset < numRows - 1) {
            chars[loop * (unitOffset * 2 - 1) + loopOffset * 2] = readChar;
        } else if (unitOffset == numRows - 1) {
            chars[(unit - 1) * loop + loopOffset] = readChar;
        } else {
            chars[loop * ((unit - unitOffset - 1) * 2 + 1) + loopOffset * 2 + 1] = readChar;
        }
    }
}
