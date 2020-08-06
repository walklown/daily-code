package com.zzp.learn.walklown.algorithm;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2]
 * 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：[2,2,2,0,1]
 * 输出：0
 *
 * @author walklown
 * @date 2020/7/22 10:10
 */
class MinArray {

    public static void main(String[] args) {
        System.out.println(minArray(new int[]{1, 3, 3}));
    }

    public static int minArray(int[] numbers) {
        int minNumIndex = mind(0, numbers.length - 1, numbers);
        return numbers[minNumIndex];
    }

    private static int mind(int left, int right, int[] numbers) {
        if (left == right) {
            return left;
        }
        int m = (left + right) / 2;
        if (numbers[m] > numbers[right]) {
            return mind(m + 1, right, numbers);
        } else if (numbers[m] == numbers[right]) {
            return mind(left, right - 1, numbers);
        } else {
            return mind(left, m, numbers);
        }
    }
}