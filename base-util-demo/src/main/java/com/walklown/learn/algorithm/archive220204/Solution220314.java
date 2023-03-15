package com.walklown.learn.algorithm.archive220204;

import java.util.ArrayList;
import java.util.List;

/**
 * 599. 两个列表的最小索引总和
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * <p>
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
 * 输出: ["Shogun"]
 * 解释: 他们唯一共同喜爱的餐厅是“Shogun”。
 * 示例 2:
 * <p>
 * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
 * 输出: ["Shogun"]
 * 解释: 他们共同喜爱且具有最小索引和的餐厅是“Shogun”，它有最小的索引和1(0+1)。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= list1.length, list2.length <= 1000
 * 1 <= list1[i].length, list2[i].length <= 30
 * list1[i] 和 list2[i] 由空格 ' ' 和英文字母组成。
 * list1 的所有字符串都是 唯一 的。
 * list2 中的所有字符串都是 唯一 的。
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220314 {

    public static void main(String[] args) {
        Solution220314 solution = new Solution220314();
        String[] result = solution.findRestaurant(new String[] {"Shogun", "Tapioca Express", "Burger King", "KFC"},
                new String[] {"KFC", "Shogun", "Burger King"});
        for (String s : result) {
            System.out.println(s);
        }
    }

    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> resultList = new ArrayList<>();
        int min = list1.length + list2.length;
        for (int i = 0; i < list1.length && i < min - 1; i++) {
            int loop = Math.min(list2.length, min - i - 1);
            for (int i1 = 0; i1 < loop; i1++) {
                if (list1[i].equals(list2[i1])) {
                    int length = i + i1 + 2;
                    if (length < min) {
                        min = length;
                        resultList = new ArrayList<>();
                        resultList.add(list1[i]);
                    } else if (length > min) {
                    } else {
                        resultList.add(list1[i]);
                    }
                }
            }
        }
        return resultList.toArray(new String[] {});
    }
}