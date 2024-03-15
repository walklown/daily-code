package com.walklown.learn.algorithm;

/**
 * 121. 买卖股票的最佳时机
 *
 * @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class Hot121 {

    public static void main(String[] args) {
        Hot121 hot = new Hot121();
        hot.maxProfit(new int[]{});
    }

    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int min = prices[0];
        int maxEarn = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else if (prices[i] - min > maxEarn) {
                maxEarn = prices[i] - min;
            }
        }
        return maxEarn;
    }
}
