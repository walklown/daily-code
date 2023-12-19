package com.walklown.learn.algorithm;

/**
 * 11. 盛最多水的容器
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot11 {

    public static void main(String[] args) {
        MiddleHot11 middleHot = new MiddleHot11();
        System.out.println(middleHot.maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
    }

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (true) {
            if (height[left] < height[right]) {
                int newContext = (right - left) * height[left];
                if (newContext > max) {
                    max = newContext;
                }
                int i;
                for (i = left + 1; i < right; i++) {
                    if (height[i] > height[left]) {
                        break;
                    }
                }
                left = i;
                if (left == right) {
                    return max;
                }
            } else {
                int newContext = height[right] * (right - left);
                if (newContext > max) {
                    max = newContext;
                }
                int i;
                for (i = right - 1; i > left; i--) {
                    if (height[i] > height[right]) {
                        break;
                    }
                }
                right = i;
                if (left == right) {
                    return max;
                }
            }
        }
    }

}
