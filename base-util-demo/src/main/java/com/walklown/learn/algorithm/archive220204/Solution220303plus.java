package com.walklown.learn.algorithm.archive220204;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LCP 40. 心算挑战
 * 「力扣挑战赛」心算项目的挑战比赛中，要求选手从 N 张卡牌中选出 cnt 张卡牌，若这 cnt 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 cnt 张卡牌数字总和。
 * 给定数组 cards 和 cnt，其中 cards[i] 表示第 i 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。
 * <p>
 * 示例 1：
 * <p>
 * 输入：cards = [1,2,8,9], cnt = 3
 * <p>
 * 输出：18
 * <p>
 * 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。
 * <p>
 * 示例 2：
 * <p>
 * 输入：cards = [3,3,1], cnt = 1
 * <p>
 * 输出：0
 * <p>
 * 解释：不存在获取有效得分的卡牌方案。
 * <p>
 * 提示：
 * <p>
 * 1 <= cnt <= cards.length <= 10^5
 * 1 <= cards[i] <= 1000
 *
 * 标签：贪心算法
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220303plus {

    public static void main(String[] args) {
        Solution220303plus solution = new Solution220303plus();
        System.out.println(solution.maxmiumScore(new int[] {1, 3, 9}, 1));
    }

    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        // 区分奇偶 & 排序
        int result = 0;
        List<Integer> singleArrays = new ArrayList<>();
        List<Integer> doubleArrays = new ArrayList<>();
        for (int i = cards.length - 1; i >= cards.length - cnt; i--) {
            result += cards[i];
            if ((cards[i] & 1) == 0) {
                // 偶数
                doubleArrays.add(cards[i]);
            } else {
                singleArrays.add(cards[i]);
            }
        }
        if ((result & 1) == 0) {
            return result;
        }
        // 一个偶数换奇数 | 一个奇数换偶数
        for (int i = cards.length - cnt - 1; i >= 0; i--) {
            if ((cards[i] & 1) == 1 && doubleArrays.size() > 0) {
                // 奇数
                return result + cards[i] - doubleArrays.get(doubleArrays.size() - 1);
            } else if ((cards[i] & 1) == 0 && singleArrays.size() > 0) {
                // 偶数
                return result + cards[i] - singleArrays.get(singleArrays.size() - 1);
            }
        }
        return 0;
    }
}
