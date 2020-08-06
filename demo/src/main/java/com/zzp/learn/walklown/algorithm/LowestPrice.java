package com.zzp.learn.walklown.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 在一个火车旅行很受欢迎的国度，你提前一年计划了一些火车旅行。在接下来的一年里，你要旅行的日子将以一个名为 days 的数组给出。每一项是一个从 1 到 365 的整数。
 * <p>
 * 火车票有三种不同的销售方式：
 * <p>
 * 一张为期一天的通行证售价为 costs[0] 美元；
 * 一张为期七天的通行证售价为 costs[1] 美元；
 * 一张为期三十天的通行证售价为 costs[2] 美元。
 * 通行证允许数天无限制的旅行。 例如，如果我们在第 2 天获得一张为期 7 天的通行证，那么我们可以连着旅行 7 天：第 2 天、第 3 天、第 4 天、第 5 天、第 6 天、第 7 天和第 8 天。
 * <p>
 * 返回你想要完成在给定的列表 days 中列出的每一天的旅行所需要的最低消费。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-cost-for-tickets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author shoujing
 * @date 2020/5/6 20:44
 */
public class LowestPrice {

    public static void main(String[] args) {
        LowestPrice lowestPrice = new LowestPrice();
        System.out.println(lowestPrice.mincostTickets(new int[]{1,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,21,24,25,27,28,29,30,31,34,37,38,39,41,43,44,45,47,48,49,54,57,60,62,63,66,69,70,72,74,76,78,80,81,82,83,84,85,88,89,91,93,94,97,99}, new int[]{9,38,134}));
    }

    /**
     * 提示：
     * <p>
     * 1 <= days.length <= 365
     * 1 <= days[i] <= 365
     * days 按顺序严格递增
     * costs.length == 3
     * 1 <= costs[i] <= 1000
     *
     * @author shoujing
     * @date 2020/5/6 21:01
     */
    public int mincostTickets(int[] days, int[] costs) {
        this.days = new ArrayList<>();
        for (int day : days) {
            this.days.add(day);
        }
        this.costs = costs;
        return dp(1);
    }

    private int[] costs;

    private List<Integer> days;

    public int dp(int i) {
        if (i > this.days.get(this.days.size()- 1)) {
            return 0;
        }
        if (this.days.contains(i)) {
            return Math.min(
                    Math.min(dp(i + 1) + this.costs[0], dp(i + 7) + this.costs[1]),
                    dp(i + 30) + this.costs[2]);
        } else {
            return dp(i + 1);
        }
    }
}
