package com.walklown.learn.algorithm.archive220204;

/**
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shoujing
 * @date 2020/5/9 21:15
 */
public class Sqrt {

    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.mySqrt(9));
    }

    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        int mid = 0;
        int result = -1;
        while (l < r) {
            mid = l + (r - l) / 2;
            long s = (long)mid * mid;
            if (s <= x) {
                result = mid;
                l = mid + 1;
            }
            if (s > x) {
                r = mid - 1;
            }
        }
        return result;
    }
}
