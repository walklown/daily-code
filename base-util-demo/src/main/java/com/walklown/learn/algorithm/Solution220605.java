package com.walklown.learn.algorithm;

import java.util.Random;

/**
 * 478. 在圆内随机生成点
 * 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
 *
 * 实现 Solution 类:
 *
 * Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置 (x_center, y_center) 初始化对象
 * randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
 *
 *
 * 示例 1：
 *
 * 输入:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1.0, 0.0, 0.0], [], [], []]
 * 输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
 * 解释:
 * Solution solution = new Solution(1.0, 0.0, 0.0);
 * solution.randPoint ();//返回[-0.02493，-0.38077]
 * solution.randPoint ();//返回[0.82314,0.38945]
 * solution.randPoint ();//返回[0.36572,0.17248]
 *
 *
 * 提示：
 *
 * 0 < radius <= 108
 * -107 <= x_center, y_center <= 107
 * randPoint 最多被调用 3 * 104 次
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220605 {

    public static void main(String[] args) {
        Solution220605 solution = new Solution220605(1.0, 0.0, 0.0);
        for (double a : solution.randPoint()) {
            System.out.println(a);
        }
        System.out.println();
        for (double a : solution.randPoint()) {
            System.out.println(a);
        }
        System.out.println();
        for (double a : solution.randPoint()) {
            System.out.println(a);
        }
    }
    Random random;
    double xc, yc, r;

    public Solution220605(double radius, double x_center, double y_center) {
        random = new Random();
        xc = x_center;
        yc = y_center;
        r = radius;
    }

    public double[] randPoint() {
        double u = random.nextDouble();
        double theta = random.nextDouble() * 2 * Math.PI;
        double rPersent = Math.sqrt(u);
        return new double[]{xc + rPersent * Math.cos(theta) * this.r, yc + rPersent * Math.sin(theta) * this.r};
    }
}
