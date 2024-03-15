package com.walklown.learn.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 215. 数组中的第K个最大元素
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot215 {

    public static void main(String[] args) {
        MiddleHot215 middleHot = new MiddleHot215();
        System.out.println(middleHot.findKthLargest(new int[]{3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        int leftSide = 0;
        int rightSide = nums.length - 1;
        return findKthLargest(nums, k, leftSide, rightSide);
    }

    public int findKthLargest(int[] nums, int k, int leftSide, int rightSide) {
        if (leftSide == rightSide) {
            return nums[leftSide];
        }
        int left = leftSide;
        int right = rightSide;
        int midOffset0 = left;
        int midOffset = left;
        left++;
        int mid = nums[midOffset];
        while (true) {
            for (; left <= right; left++) {
                if (nums[left] == mid) {
                    midOffset++;
                } else if (nums[left] < mid) {
                    nums[midOffset0] = nums[left];
                    nums[left] = mid;
                    midOffset0++;
                    midOffset++;
                } else {
                    break;
                }
            }
            for (; left <= right; right--) {
                if (nums[right] == mid) {
//                    nums[midOffset] = nums[right];
                    nums[right] = nums[left];
                    nums[left] = mid;
                    midOffset++;
                    left++;
                    right--;
                    break;
                } else if (nums[right] < mid) {
                    nums[midOffset0] = nums[right];
                    nums[right] = nums[left];
                    nums[left] = mid;
                    midOffset0++;
                    midOffset++;
                    left++;
                    right--;
                    break;
                }
            }
            if (left > right) {
                break;
            }
        }
        if (k <= rightSide - midOffset) {
            return findKthLargest(nums, k, midOffset + 1, rightSide);
        } else if (k >= rightSide - midOffset + 1 && k <= rightSide - midOffset0 + 1) {
            return nums[midOffset];
        } else if (k == rightSide - midOffset0) {
            return findMin(nums, midOffset0 + 1, rightSide);
        } else {
            return findKthLargest(nums, k - (rightSide - midOffset0) - 1, leftSide, midOffset0 - 1);
        }
    }

    public int findMin(int[] nums, int leftSide, int rightSide) {
        int min = nums[leftSide];
        for (int i = leftSide + 1; i <= rightSide; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }
}
