//package com.zzp.learn.walklown.algorithm;
//
///**
// * 440. 字典序的第K小数字
// * 给定整数 n 和 k，返回  [1, n] 中字典序第 k 小的数字。
// * <p>
// * <p>
// * <p>
// * 示例 1:
// * <p>
// * 输入: n = 13, k = 2
// * 输出: 10
// * 解释: 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
// * 示例 2:
// * <p>
// * 输入: n = 1, k = 1
// * 输出: 1
// * <p>
// * <p>
// * 提示:
// * <p>
// * 1 <= k <= n <= 109
// *
// * @author walklown
// * @date 2020/8/11 22:42
// */
//class Solution220323 {
//
//    // 1, 10, 100, 101, .. , 109, 11, 110, .. , 12, 13, 2, 3, 4, 5, 6, 7, 8, 9
//
//    public static void main(String[] args) {
//        Solution220323 solution = new Solution220323();
////        int far = solution.findTarget();
////        System.out.println(far);
//    }
//
//    public int findKthNumber(int n, int k) {
//        // 最高位位数
//        int num = 0;
//        int i = n;
//        // 最高位的值
//        int j = n;
//        // 整数
//        int base = 1;
//        while (i > 0) {
//            num++;
//            if (i < 10) {
//                base *= i;
//                j = i;
//            }
//            i /= 10;
//            base *= 10;
//        }
//        // 余数
//        int more = n - base;
//        // 分三段，左边，最大段，右边
//        for (int l = 1; l < 11; l++) {
//            int minus = 1;
//            if (l <= j) {
//                for (int m = 0; m < j - 1; m++) {
//                    if (k  <=  minus) {
//                        return findKthNumber(, k);
//                    }
//                    k -= minus;
//                    minus *= 10;
//                }
//                if (l == j) {
//                    if (k  <=  more) {
//
//                    }
//                    k -= more;
//                }
//            } else {
//                for (int m = 0; m < j - 2; m++) {
//                    if (k  <=  minus) {
//
//                    }
//                    k -= minus;
//                    minus *= 10;
//                }
//            }
//        }
//    }
//
//    int getValue(int num, int start, int k) {
//
//    }
//}