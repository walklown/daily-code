package com.walklown.learn.algorithm;

/**
 * 7. 整数反转
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot7 {

    public static void main(String[] args) {
        MiddleHot7 middleHot = new MiddleHot7();
        System.out.println(middleHot.reverse(1563847412));
//        System.out.println(Integer.MAX_VALUE);
    }

    public int reverse(int x) {
        if (x < 10 && x > -10) {
            return x;
        }
        // 2147483647 -2147483648
        if (x > Integer.MAX_VALUE - 5 || x < Integer.MIN_VALUE + 6) {
            return 0;
        }
        int q;
        if (x < 0) {
            x = -x;
            q = -1;
        } else {
            q = 1;
        }
        int size;
        byte[] value = new byte[10];
        int nextValue = x;
        for (size = 0; size < value.length; ) {
            value[size] = (byte) (nextValue % 10);
            nextValue = nextValue / 10;
            size++;
            if (nextValue == 0) {
                break;
            }
        }
        if (size == value.length) {
            byte[] maxIntByte = new byte[] {2, 1, 4, 7, 4, 8, 3, 6, 4, 7};
            for (int i = 0; i < size; i++) {
                if (value[i] > maxIntByte[i]) {
                    return 0;
                } else if (value[i] < maxIntByte[i]) {
                    break;
                }
            }
        }
        int result = value[0];
        for (int i = 1; i < size; i++) {
            result = result * 10 + value[i];
        }
        return result * q;
    }

}
