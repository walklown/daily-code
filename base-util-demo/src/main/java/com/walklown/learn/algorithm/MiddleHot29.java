package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

/**
 * 29. 两数相除
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot29 {

    public static void main(String[] args) {
        MiddleHot29 middleHot = new MiddleHot29();
        System.out.println(JacksonUtils.toJSONString(middleHot.divide(2147483647, 2)));
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            } else {
                return -dividend;
            }
        }
        int pre = 1;
        String dividendStr = String.valueOf(dividend);
        String divisorStr = String.valueOf(divisor);
        if (dividend < 0) {
            dividendStr = dividendStr.substring(1);
            pre = -pre;
        }
        if (divisor < 0) {
            divisorStr = divisorStr.substring(1);
            divisor = -divisor;
            pre = -pre;
        }
        if (dividendStr.length() < divisorStr.length()
                || (dividendStr.length() == divisorStr.length() && dividendStr.compareTo(divisorStr) < 0)) {
            return 0;
        }
        int dividendItem = Integer.parseInt("-" + dividendStr.substring(0, divisorStr.length()));
        int value = 0;
        for (int i = divisorStr.length() - 1; ; ) {
            while (true) {
                if (dividendItem > -divisor) {
                    break;
                }
                dividendItem += divisor;
                value++;
            }
            i++;
            if (i == dividendStr.length()) {
                break;
            }
            if (dividendItem == 0) {
                dividendItem = Integer.parseInt("-" + String.valueOf(dividendItem) + dividendStr.charAt(i));
            } else {
                dividendItem = Integer.parseInt(String.valueOf(dividendItem) + dividendStr.charAt(i));
            }
            value = Integer.parseInt(value + "0");
        }
        if (pre < 0) {
            return -value;
        }
        return value;
    }
}
