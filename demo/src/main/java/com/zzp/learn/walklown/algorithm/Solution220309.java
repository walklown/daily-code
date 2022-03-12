//package com.zzp.learn.walklown.algorithm;
//
///**
// * 798. 得分最高的最小轮调
// * 给你一个数组 nums，我们可以将它按一个非负整数 k 进行轮调，这样可以使数组变为 [nums[k], nums[k + 1], ... nums[nums.length - 1], nums[0], nums[1], ..., nums[k-1]] 的形式。此后，任何值小于或等于其索引的项都可以记作一分。
// * <p>
// * 例如，数组为 nums = [2,4,1,3,0]，我们按 k = 2 进行轮调后，它将变成 [1,3,0,2,4]。这将记为 3 分，因为 1 > 0 [不计分]、3 > 1 [不计分]、0 <= 2 [计 1 分]、2 <= 3 [计 1 分]，4 <= 4 [计 1 分]。
// * 在所有可能的轮调中，返回我们所能得到的最高分数对应的轮调下标 k 。如果有多个答案，返回满足条件的最小的下标 k 。
// * <p>
// * <p>
// * <p>
// * 示例 1：
// * <p>
// * 输入：nums = [2,3,1,4,0]
// * 输出：3
// * 解释：
// * 下面列出了每个 k 的得分：
// * k = 0,  nums = [2,3,1,4,0],    score 2
// * k = 1,  nums = [3,1,4,0,2],    score 3
// * k = 2,  nums = [1,4,0,2,3],    score 3
// * k = 3,  nums = [4,0,2,3,1],    score 4
// * k = 4,  nums = [0,2,3,1,4],    score 3
// * 所以我们应当选择 k = 3，得分最高。
// * 示例 2：
// * <p>
// * 输入：nums = [1,3,0,2,4]
// * 输出：0
// * 解释：
// * nums 无论怎么变化总是有 3 分。
// * 所以我们将选择最小的 k，即 0。
// * <p>
// * <p>
// * 提示：
// * <p>
// * 1 <= nums.length <= 105
// * 0 <= nums[i] < nums.length
// *
// * @author 守愚（张智沛）
// * @date 2022-02-17
// */
//class Solution220309 {
//
//    public static void main(String[] args) {
//        Solution220309 solution = new Solution220309();
//        int result = solution.bestRotation(new int[] {2, 3, 1, 4, 0});
//        System.out.println(result);
//    }
//
//    public int bestRotation(int[] nums) {
//        int[] diff = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] <= i) {
//                // 0可以
//                // j == 论调 - 1
//                fromZero[i]++;
////                for (int j = 0; j <= i - nums[i]; j++) {
////                    scores[j]++;
////                }
//                // i+1 可以
//                if (i + 1 < nums.length) {
//                    toLength[i + 1]++;
//                } else {
//                    toLength[i]++;
//                }
////                for (int j = i + 1; j < nums.length; j++) {
////                    scores[j]++;
////                }
//            } else if (nums[i] < nums.length) {
//                // 0 不可以，i+1 可以
//                for (int j = i + 1; j <= nums.length - (nums[i] - i); j++) {
//                    scores[j]++;
//                }
//            }
//        }
//        int maxChange = 0;
//        int max = 0;
//        for (int i = 0; i < nums.length; i++) {
//            int sum = scores[i];
//            for (int j = i; j < nums.length; j++) {
//                sum += fromZero[j];
//            }
//            for (int j = 0; j <= i; j++) {
//                sum += toLength[j];
//            }
//            if (sum > max) {
//                max = sum;
//                maxChange = i;
//            }
//        }
//        return maxChange;
//    }
//}
