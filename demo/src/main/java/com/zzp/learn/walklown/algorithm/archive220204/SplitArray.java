package com.zzp.learn.walklown.algorithm.archive220204;

class SplitArray {

    public static void main(String[] args) {
        System.out.println(splitArray(new int[]{1, 2147483646}, 1));
    }

    public static int splitArray(int[] nums, int m) {
        int minMaxSum = 0;
        int threshold = 0;
        for (int num : nums) {
            minMaxSum = Math.max(minMaxSum, num);
            threshold += num;
        }
        if (nums.length == m) {
            return minMaxSum;
        }
        while (minMaxSum < threshold) {
            int mid = (int) (((long) threshold + minMaxSum) / 2);
            int n = 1;
            int sum = 0;
            for (int num : nums) {
                sum += num;
                if (sum > mid) {
                    sum = num;
                    n++;
                    if (n > m) {
                        minMaxSum = mid + 1;
                        break;
                    }
                }
            }
            if (n <= m) {
                threshold = mid;
            }
        }
        return minMaxSum;
    }
}