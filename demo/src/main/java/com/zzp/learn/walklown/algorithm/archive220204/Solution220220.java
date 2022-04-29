package com.zzp.learn.walklown.algorithm.archive220204;

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
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220220 {

    public static void main(String[] args) {
        Solution220220 solution = new Solution220220();
        System.out.println(solution.isOneBitCharacter(new int[]{1, 0, 0}));
    }

    public boolean isOneBitCharacter(int[] bits) {
        int offset = 0;
        for (int i = bits.length - 2; i >= 0; i--) {
            if (bits[i] == 0) {
                offset = i + 1;
                break;
            }
        }
        // 处理最后一个
        if ((bits.length - 1 - offset) % 2 == 1) {
            // 双
            return false;
        } else {
            // 单
            return true;
        }
    }
}
