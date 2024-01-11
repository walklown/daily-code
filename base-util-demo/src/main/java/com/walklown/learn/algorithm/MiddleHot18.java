package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 18. 四数之和
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot18 {

    public static void main(String[] args) {
        MiddleHot18 middleHot = new MiddleHot18();
        System.out.println(
                JacksonUtils.toJSONString(middleHot.fourSum(new int[] {0,0,0,0}, 0)));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length < 4) {
            return list;
        }
        if (nums.length == 4) {
            int part1 = nums[0] + nums[1];
            int part2 = nums[2] + nums[3];
            int compare;
            if (part1 > 0 && part2 > 0) {
                if (target > part1) {
                    compare = Integer.compare(part2, target - part1);
                } else {
                    compare = 1;
                }
            } else if (part1 < 0 && part2 < 0) {
                if (target < part1) {
                    compare = Integer.compare(part2, target - part1);
                } else {
                    compare = -1;
                }
            } else {
                compare = Integer.compare(part2 + part1, target);
            }
            if (compare == 0) {
                List<Integer> itemList = new ArrayList<>();
                itemList.add(nums[0]);
                itemList.add(nums[1]);
                itemList.add(nums[2]);
                itemList.add(nums[3]);
                list.add(itemList);
            }
            return list;
        }
        // 冒泡排序，从大到小
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
            if (first == nums.length + 1 && nums[i] < 0) {
                first = i;
            }
        }
        for (int a = 0; a <= nums.length - 3; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            for (int i = a + 1; i <= nums.length - 2; i++) {
                int j = i + 1;
                int k = nums.length - 1;
                for (; k > j; ) {
                    int part1 = nums[a] + nums[i];
                    int part2 = nums[j] + nums[k];
                    int compare;
                    if (part1 > 0 && part2 > 0) {
                        if (target > part1) {
                            compare = Integer.compare(part2, target - part1);
                        } else {
                            compare = 1;
                        }
                    } else if (part1 < 0 && part2 < 0) {
                        if (target < part1) {
                            compare = Integer.compare(part2, target - part1);
                        } else {
                            compare = -1;
                        }
                    } else {
                        compare = Integer.compare(part2 + part1, target);
                    }
                    int s;
                    if (compare == 0) {
                        List<Integer> value = new ArrayList<>();
                        value.add(nums[a]);
                        value.add(nums[i]);
                        value.add(nums[j]);
                        value.add(nums[k]);
                        list.add(value);
                        s = 0;
                    } else if (compare < 0) {
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
                while (i + 1 <= nums.length - 2 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return list;
    }

}
