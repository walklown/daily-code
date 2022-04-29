package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution200813 {

    public static void main(String[] args) {
        Solution200813 solution = new Solution200813();
        System.out.println(solution.multiply("123", "456"));
    }

    private static final int ZERO_CHAR = '0';

    public String multiply(String num1, String num2) {
        if ("0".endsWith(num1) || "0".equals(num2)) {
            return "0";
        }
        String result = "";
        int[] num1Array = new int[num1.length()];
        int[] num2Array = new int[num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            num1Array[i] = num1.charAt(num1.length() - i - 1) - ZERO_CHAR;
        }
        for (int i = 0; i < num2.length(); i++) {
            num2Array[i] = num2.charAt(num2.length() - i - 1) - ZERO_CHAR;
        }
        int cache = 0;
        for (int i = 0; i < num1Array.length + num2Array.length - 1; i++) {
            for (int j = 0; j <= i && j < num1Array.length; j++) {
                if (i - j < num2Array.length) {
                    cache += num1Array[j] * num2Array[i - j];
                }
            }
            int num = cache % 10;
            cache = cache / 10;
            result = num + result;
        }
        if (cache > 0) {
            result = cache + result;
        }
        if (result.charAt(0) == '0') {
            result = result.substring(1);
        }
        return result;
    }
}