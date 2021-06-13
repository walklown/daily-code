package com.zzp.learn.walklown.algorithm;

/**
 * 278. 第一个错误的版本
 * <p>
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 * <p>
 * 示例:
 * <p>
 * 给定 n = 5，并且 version = 4 是第一个错误的版本。
 * <p>
 * 调用 isBadVersion(3) -> false
 * 调用 isBadVersion(5) -> true
 * 调用 isBadVersion(4) -> true
 * <p>
 * 所以，4 是第一个错误的版本。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-bad-version
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution210613 {

    public static void main(String[] args) {
        Solution210613 solution210613 = new Solution210613();
        System.out.println(solution210613.firstBadVersion(10));
    }

    public int firstBadVersion(int n) {
        return firstBadVersion(0, n);
    }

    public int firstBadVersion(int lastGood, int firstBad) {
        if (firstBad == lastGood + 1) {
            return firstBad;
        }
        int middle = lastGood + (firstBad - lastGood) / 2;
        if (isBadVersion(middle)) {
            return firstBadVersion(0, middle);
        } else {
            return firstBadVersion(middle, firstBad);
        }
    }

    boolean isBadVersion(int version) {
        return version >= 8;
    }
}
