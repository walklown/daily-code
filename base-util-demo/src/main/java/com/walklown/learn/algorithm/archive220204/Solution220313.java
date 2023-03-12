package com.walklown.learn.algorithm.archive220204;

/**
 * 393. UTF-8 编码验证
 * 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
 * <p>
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 这是 UTF-8 编码的工作方式：
 * <p>
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：data = [197,130,1]
 * 输出：true
 * 解释：数据表示字节序列:11000101 10000010 00000001。
 * 这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
 * 示例 2：
 * <p>
 * 输入：data = [235,140,4]
 * 输出：false
 * 解释：数据表示 8 位的序列: 11101011 10001100 00000100.
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个 3 字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= data.length <= 2 * 104
 * 0 <= data[i] <= 255
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220313 {

    public static void main(String[] args) {
        Solution220313 solution = new Solution220313();
        System.out.println(solution.validUtf8(new int[] {240, 162, 138, 147, 145}));
    }

    private int seven = 2 * 2 * 2 * 2 * 2 * 2 * 2;

    private int six = 2 * 2 * 2 * 2 * 2 * 2;

    private int five = 2 * 2 * 2 * 2 * 2;

    private int four = 2 * 2 * 2 * 2;

    private int three = 2 * 2 * 2;

    private int oneBitFirstMark = seven;

    private int twoBitFirstMark = seven + six;

    private int threeBItFirstMark = seven + six + five;

    private int fourBItFirstMark = seven + six + five + four;

    private int fiveBItFirstMark = seven + six + five + four + three;

    public boolean validUtf8(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int bitNum = checkFirst(data[i]);
            if (bitNum == -1) {
                return false;
            }
            if (bitNum == 1) {
                continue;
            }
            int end = i + bitNum - 1;
            if (end > data.length) {
                return false;
            }
            for (; i < end; i++) {
                if (!checkTail(data[i + 1])) {
                    return false;
                }
            }
        }
        return true;
    }

    private int checkFirst(int first) {
        if ((first & seven) == 0) {
            // 一位
            return 1;
        } else if ((first & threeBItFirstMark) == twoBitFirstMark) {
            return 2;
        } else if ((first & fourBItFirstMark) == threeBItFirstMark) {
            return 3;
        } else if ((first & fiveBItFirstMark) == fourBItFirstMark) {
            return 4;
        } else {
            return -1;
        }
    }

    private boolean checkTail(int tail) {
        if ((tail & twoBitFirstMark) == oneBitFirstMark) {
            return true;
        } else {
            return false;
        }
    }
}