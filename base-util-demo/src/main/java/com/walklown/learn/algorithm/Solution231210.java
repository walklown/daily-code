package com.walklown.learn.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 70. 爬楼梯
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class Solution231210 {

    public static void main(String[] args) {
        Solution231210 solution = new Solution231210();
        System.out.println(solution.climbStairs(4));
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[] time = new int[n];
        time[0] = 1;
        time[1] = 2;
        for (int i = 2; i < n; i++) {
            time[i] = time[i - 1] + time[i - 2];
        }
        return time[n - 1];
    }
}
