package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

/**
 * 912. 排序数组
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot912 {

    public static void main(String[] args) {
        MiddleHot912 middleHot = new MiddleHot912();
        System.out.println(JacksonUtils.toJSONString(middleHot.sortArray(new int[]{5,1,1,2,0,0})));
    }

    public int[] sortArray(int[] nums) {
        int[] sort = new int[100001];
        for (int i = 0; i < nums.length; i++) {
            sort[nums[i] + 50000]++;
        }
        int offset = 0;
        for (int i = 0; i < 100001; i++) {
            if (sort[i] > 0) {
                int value = i - 50000;
                for (int j = 0; j < sort[i]; j++) {
                    nums[offset] = value;
                    offset++;
                }
            }
        }
        return nums;
//        sortArray(nums, 0, nums.length - 1);
//        return nums;
    }

//    public int[] sortArray(int[] nums, int leftSide, int rightSide) {
//        if (leftSide - rightSide == 1) {
//            return nums;
//        }
//        int midOffsetL = leftSide;
//        int midOffsetR = leftSide;
//        int mid = nums[midOffsetR];
//        int left = leftSide + 1;
//        int right = rightSide;
//        while (true) {
//            for (; left <= right; ) {
//                if (nums[left] == mid) {
//                    midOffsetR++;
//                    left++;
//                } else if (nums[left] < mid) {
//                    nums[midOffsetL] = nums[left];
//                    nums[left] = mid;
//                    midOffsetL++;
//                    midOffsetR++;
//                    left++;
//                } else {
//                    break;
//                }
//            }
//            for (; left <= right; right--) {
//                if (nums[right] <= mid) {
//                    nums[midOffsetL] = nums[right];
//                    nums[right] = nums[left];
//                    nums[left] = mid;
//                    left++;
//                    right--;
//                    midOffsetL++;
//                    midOffsetR++;
//                    break;
//                }
//            }
//            if (left > right) {
//                break;
//            }
//        }
//        sortArray(nums, leftSide, midOffsetL - 1);
//        sortArray(nums, midOffsetR + 1, rightSide);
//        return nums;
//    }
}
