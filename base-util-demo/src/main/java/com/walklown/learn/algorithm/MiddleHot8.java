package com.walklown.learn.algorithm;

/**
 * 8. 字符串转换整数 (atoi)
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot8 {

    public static void main(String[] args) {
        MiddleHot8 middleHot = new MiddleHot8();
        System.out.println(middleHot.myAtoi("  -0000000000012345678"));
//        System.out.println(Integer.MAX_VALUE);
    }

    public int myAtoi(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        byte[] values = new byte[10];
        boolean p = true;
        int i = 0;
        char firstChar = ' ';
        for (; i < s.length(); i++) {
            char a = s.charAt(i);
            if (a == ' ') {
                continue;
            }
            firstChar = a;
            break;
        }
        if (i == s.length()) {
            return 0;
        }
        if (firstChar == '-') {
            p = false;
            i++;
        } else if (firstChar == '+') {
            i++;
        }
        for (; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                continue;
            }
            break;
        }
        // 2147483647 -2147483648
        int j = 0;
        for (; i < s.length(); i++,j++) {
            int num = s.charAt(i) - '0';
            if (num > 9 || num < 0) {
                // Not number
                break;
            }
            // Is number
            if (j == 10) {
                if (p) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            values[j] = (byte) num;
        }
        if (j == 10) {
            // If Max
            byte[] maxIntByte;
            if (p) {
                maxIntByte = new byte[] {2, 1, 4, 7, 4, 8, 3, 6, 4, 7};
            } else {
                maxIntByte = new byte[] {2, 1, 4, 7, 4, 8, 3, 6, 4, 8};
            }
            for (int k = 0; k <10; k++) {
                if (values[k] > maxIntByte[k]) {
                    if (p) {
                        return Integer.MAX_VALUE;
                    } else {
                        return Integer.MIN_VALUE;
                    }
                } else if (values[k] < maxIntByte[k]) {
                    break;
                }
            }
        }
        int result = values[0];
        for (int k = 1; k < j; k++) {
            result = result * 10 + values[k];
        }
        if (!p) {
            return result * -1;
        }
        return result;
    }

}
