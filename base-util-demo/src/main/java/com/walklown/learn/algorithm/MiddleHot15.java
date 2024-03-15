package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 15. 三数之和
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot15 {

    public static void main(String[] args) {
        MiddleHot15 middleHot = new MiddleHot15();
        System.out.println(JacksonUtils.toJSONString(middleHot.threeSum(new int[] {-1,0,1,2,-1,-4})));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        // 冒泡排序，从大到小
        int last = -1;
        int first = nums.length + 1;
        int lastMax = 0;
        for (int i = 0; i < nums.length; i++) {
            int maxOffset = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[maxOffset] < nums[j]) {
                    maxOffset = j;
                    if (i > 0 && nums[maxOffset] == lastMax) {
                        break;
                    }
                }
            }
            int a = nums[i];
            nums[i] = nums[maxOffset];
            nums[maxOffset] = a;
            lastMax = nums[i];
            if (nums[i] > 0) {
                last = i;
            }
            if (first == nums.length + 1 && nums[i] < 0) {
                first = i;
            }
        }
        List<List<Integer>> list = new ArrayList<>();
        if (first - last > 3) {
            List<Integer> value = new ArrayList<>();
            value.add(0);
            value.add(0);
            value.add(0);
            list.add(value);
        }
        if (last == -1 || first == nums.length + 1) {
            return list;
        }
        for (int i = 0; i <= last; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            for (; k > j;) {
                int num = nums[i] + nums[j] + nums[k];
                int s;
                if (num == 0) {
                    List<Integer> value = new ArrayList<>();
                    value.add(nums[i]);
                    value.add(nums[j]);
                    value.add(nums[k]);
                    list.add(value);
                    s = 0;
                } else if (num < 0) {
                    s = -1;
                } else {
                    s = 1;
                }
                if (s != -1) {
                    j++;
                    while (j < k && nums[j - 1] == nums[j]) {
                        j++;
                    }
                }
                if (s != 1) {
                    k--;
                    while (k > j && nums[k + 1] == nums[k]) {
                        k--;
                    }
                }
            }
            while (i + 1 <= last && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        return list;
    }

}
