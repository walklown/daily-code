package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

/**
 * 31. 下一个排列
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot31 {

    public static void main(String[] args) {
        MiddleHot31 middleHot = new MiddleHot31();
        int[] a = new int[]{1, 2, 6, 5, 4, 3}; // 1, 3, 2, 4, 5, 6  offset = 1
        middleHot.nextPermutation(a);
        System.out.println(JacksonUtils.toJSONString(a));
    }

    public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }
        int middle = nums[nums.length - 1];
        if (nums.length == 2) {
            // 总是交换
            nums[1] = nums[0];
            nums[0] = middle;
            return;
        }
        Integer lastNotMax = null;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                lastNotMax = i;
                break;
            }
        }
        if (lastNotMax == null) {
            // 全反
            int first = 0;
            int last = nums.length - 1;
            for (int i = 0; i < nums.length / 2; i++) {
                middle = nums[first];
                nums[first] = nums[last];
                nums[last] = middle;
            }
            return;
        }
        //
        if (nums.length - 1 - lastNotMax > 2) {
            int first = lastNotMax + 2;
            int last = nums.length - 1;
            for (int i = 0; i < nums.length / 2; i++) {
                middle = nums[first];
                nums[first] = nums[last];
                nums[last] = middle;
            }
        }
    }
}
