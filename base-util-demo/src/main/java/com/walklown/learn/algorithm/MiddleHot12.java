package com.walklown.learn.algorithm;

/**
 * 12. 整数转罗马数字
 *
 * @author 守愚（张智沛）
 */
public class MiddleHot12 {

    public static void main(String[] args) {
        MiddleHot12 middleHot = new MiddleHot12();
        System.out.println(middleHot.intToRoman(1994));
    }

    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        if (num > 999) {
            int i = num / 1000;
            num = num % 1000;
            for (; i > 0; i--) {
                builder.append("M");
            }
        }
        if (num > 99) {
            int i = num / 100;
            num = num % 100;
            if (i == 9) {
                i = 0;
                builder.append("CM");
            } else if (i >= 5) {
                i -= 5;
                builder.append("D");
            } else if (i == 4) {
                i = 0;
                builder.append("CD");
            }
            if (i > 0) {
                for (; i > 0; i--) {
                    builder.append("C");
                }
            }
        }
        if (num > 9) {
            int i = num / 10;
            num = num % 10;
            if (i == 9) {
                i = 0;
                builder.append("XC");
            } else if (i >= 5) {
                i -= 5;
                builder.append("L");
            } else if (i == 4) {
                i = 0;
                builder.append("XL");
            }
            if (i > 0) {
                for (; i > 0; i--) {
                    builder.append("X");
                }
            }
        }
        int i = num;
        if (i == 9) {
            i = 0;
            builder.append("IX");
        } else if (i >= 5) {
            i -= 5;
            builder.append("V");
        } else if (i == 4) {
            i = 0;
            builder.append("IV");
        }
        if (i > 0) {
            for (; i > 0; i--) {
                builder.append("I");
            }
        }
        return builder.toString();
    }

}
