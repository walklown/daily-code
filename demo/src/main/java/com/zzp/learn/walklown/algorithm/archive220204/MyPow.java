package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * @author shoujing
 * @date 2020/5/11 23:07
 */
public class MyPow {

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        System.out.println(myPow.myPow(2, -2147483648));
    }

    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / pow(x, -(long) n);
        } else {
            return pow(x, n);
        }
    }

    private double pow(double x, long n) {
        long i = n / 2;
        long j = n % 2;
        double s;
        if (i > 0) {
            double child = pow(x, i);
            s = child * child;
            if (j == 1) {
                s = s * x;
            }
        } else {
            if (j == 1) {
                s = x;
            } else {
                s = 1;
            }
        }
        return s;
    }
}
