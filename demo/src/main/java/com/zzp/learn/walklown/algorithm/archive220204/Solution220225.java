package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 537. 复数乘法
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 *
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 *
 *
 *
 * 示例 1：
 *
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * 示例 2：
 *
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 *
 *
 * 提示：
 *
 * num1 和 num2 都是有效的复数表示。
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220225 {

    public static void main(String[] args) {
        Solution220225 solution = new Solution220225();
        System.out.println(solution.complexNumberMultiply("1+0i", "1+0i"));
    }

    public String complexNumberMultiply(String num1, String num2) {
        String[] num1Sub = num1.split("\\+");
        int num1Int = Integer.parseInt(num1Sub[0]);
        int num1Int2 = Integer.parseInt(num1Sub[1].substring(0, num1Sub[1].length() - 1));
        String[] num2Sub = num2.split("\\+");
        int num2Int = Integer.parseInt(num2Sub[0]);
        int num2Int2 = Integer.parseInt(num2Sub[1].substring(0, num2Sub[1].length() - 1));
        int num3Int = num1Int * num2Int - num1Int2 * num2Int2;
        int num3Int2 = num1Int * num2Int2 + num2Int * num1Int2;
        return num3Int + "+" + num3Int2 + "i";
    }
}
