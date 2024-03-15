package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 16. 最接近的三数之和
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot16 {

    public static void main(String[] args) {
        MiddleHot16 middleHot = new MiddleHot16();
        System.out.println(JacksonUtils.toJSONString(middleHot.threeSumClosest(new int[] {-1,2,1,-4}, 1)));
    }

    public int threeSumClosest(int[] nums, int target) {
        int value = 0;
        int minus = 20001;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int testValue = nums[i] + nums[j] + nums[k];
                    int testMinus;
                    if (testValue > target) {
                        testMinus = testValue - target;
                    } else {
                        testMinus = target - testValue;
                    }
                    if (testMinus < minus) {
                        if (testMinus == 0) {
                            return testValue;
                        }
                        value = testValue;
                        minus = testMinus;
                    }
                }
            }
        }
        return value;
    }

}
