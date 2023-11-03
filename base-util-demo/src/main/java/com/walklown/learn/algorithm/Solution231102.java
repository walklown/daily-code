package com.walklown.learn.algorithm;

/**
 * <a href="https://leetcode.cn/problems/rings-and-rods/?envType=daily-question&envId=2023-11-02">2103. 环和杆</a>
 */
class Solution231102 {

    public static void main(String[] args) {
        Solution231102 solution = new Solution231102();
        System.out.println(solution.countPoints("B0B6G0R6R0R6G9"));
    }
    public int countPoints(String rings) {
        if (rings.length() < 5) {
            return 0;
        }
        int[][] count = new int[10][3];

        for (int i = 0; i < rings.length(); i+=2) {
            if ('R' == rings.charAt(i)) {
                count[rings.charAt(i + 1) - 48][0] = 1;
            } else if ('G' == rings.charAt(i)) {
                count[rings.charAt(i + 1) - 48][1] = 1;
            } else if ('B' == rings.charAt(i)) {
                count[rings.charAt(i + 1) - 48][2] = 1;
            }
        }
        int num = 0;
        for (int i = 0; i < 10; i++) {
            if ((count[i][0] & count[i][1] & count[i][2]) == 1) {
                num++;
            }
        }
        return num;
    }
}
