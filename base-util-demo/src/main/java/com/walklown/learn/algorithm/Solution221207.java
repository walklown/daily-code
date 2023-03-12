package com.walklown.learn.algorithm;

/**
 * 1775. 通过最少操作次数使数组的和相等
 * 给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
 * <p>
 * 每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
 * <p>
 * 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
 * 输出：3
 * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
 * - 将 nums2[0] 变为 6 。 nums1 = [1,2,3,4,5,6], nums2 = [6,1,2,2,2,2] 。
 * - 将 nums1[5] 变为 1 。 nums1 = [1,2,3,4,5,1], nums2 = [6,1,2,2,2,2] 。
 * - 将 nums1[2] 变为 2 。 nums1 = [1,2,2,4,5,1], nums2 = [6,1,2,2,2,2] 。
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,1,1,1,1,1,1], nums2 = [6]
 * 输出：-1
 * 解释：没有办法减少 nums1 的和或者增加 nums2 的和使二者相等。
 * 示例 3：
 * <p>
 * 输入：nums1 = [6,6], nums2 = [1]
 * 输出：3
 * 解释：你可以通过 3 次操作使 nums1 中所有数的和与 nums2 中所有数的和相等。以下数组下标都从 0 开始。
 * - 将 nums1[0] 变为 2 。 nums1 = [2,6], nums2 = [1] 。
 * - 将 nums1[1] 变为 2 。 nums1 = [2,2], nums2 = [1] 。
 * - 将 nums2[0] 变为 4 。 nums1 = [2,2], nums2 = [4] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 105
 * 1 <= nums1[i], nums2[i] <= 6
 */
class Solution221207 {

    public static void main(String[] args) {
        Solution221207 solution = new Solution221207();
        System.out.println(solution.minOperations(new int[] {1},
                new int[] {6}));
    }

    public int minOperations(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length * 6 || nums2.length > nums1.length * 6) {
            return -1;
        }
        int value = 0;
        int[] numsOf1 = new int[] {0, 0, 0, 0, 0, 0, 0};
        int[] numsOf2 = new int[] {0, 0, 0, 0, 0, 0, 0};
        for (int j : nums1) {
            value += j;
            numsOf1[j]++;
        }
        for (int i : nums2) {
            value -= i;
            numsOf2[i]++;
        }
        if (value == 0) {
            return 0;
        }
        int[] large;
        int[] small;
        if (value > 0) {
            large = numsOf1;
            small = numsOf2;
        } else {
            large = numsOf2;
            small = numsOf1;
            value = -value;
        }
        return orderInsert(0, 0, value, large, small);
    }

    private int orderInsert(int num, int cursor, int value, int[] large, int[] small) {
        int numOf5 = (large[6 - cursor] + small[1 + cursor]);
        int least = value - numOf5 * (5 - cursor);
        if (least < 0) {
            num += value / (5 - cursor);
            if (value % (5 - cursor) > 0) {
                num++;
            }
            return num;
        } else {
            num += numOf5;
            return orderInsert(num, cursor + 1, least, large, small);
        }
    }
}
