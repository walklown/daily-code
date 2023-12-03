package com.walklown.learn.algorithm.archive220204;

import java.util.LinkedList;
import java.util.List;

/**
 * 969. 煎饼排序
 * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * <p>
 * 一次煎饼翻转的执行过程如下：
 * <p>
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 * <p>
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 *
 * @author Walklown
 * @date 2022-02-17
 */
class Solution220219 {

    public static void main(String[] args) {
        Solution220219 solution = new Solution220219();
        System.out.println(solution.pancakeSort(new int[] {3, 2, 4, 1}));
    }

    public List<Integer> pancakeSort(int[] arr) {
        int[] orderArr = new int[arr.length];
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            int max = 0;
            int order = -1;
            for (int j = 0; j < arr.length; j++) {
                if (max < arr[j]) {
                    max = arr[j];
                    order = j;
                }
            }
            orderArr[i] = order;
            arr[order] = 0;
        }
        for (int i = 0; i < orderArr.length; i++) {
            if (orderArr.length - 1 - i == orderArr[i]) {
                continue;
            }
            if (0 != orderArr[i]) {
                result.add(orderArr[i] + 1);
            }
            result.add(orderArr.length - i);
            for (int j = i + 1; j < orderArr.length; j++) {
                // 计算i
                if (0 != orderArr[i] && orderArr[i] > orderArr[j]) {
                    orderArr[j] = orderArr.length - i - (orderArr[i] - orderArr[j]) - 1;
                } else {
                    orderArr[j] = orderArr.length - i - orderArr[j] - 1;
                }
            }
            orderArr[i] = arr.length - i - 1;
        }
        return result;
    }
}
