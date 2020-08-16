package com.zzp.learn.walklown.algorithm;

/**
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 * <p>
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 * <p>
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 * <p>
 * 最后返回经过上色渲染后的图像。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 * 解析:
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点。
 * 注意:
 * <p>
 * image 和 image[0] 的长度在范围 [1, 50] 内。
 * 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
 * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
 *
 * @author walklown
 * @date 2020/8/16 22:00
 */
class Solution200816 {

    public static void main(String[] args) {
        Solution200816 solution200816 = new Solution200816();
        int[][] image = new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] result = solution200816.floodFill(image, 1, 1, 2);
        for (int[] a : result) {
            for (int i : a) {
                System.out.printf(i + " ");
            }
            System.out.printf("\n");
        }
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int fromColor = image[sr][sc];
        if (fromColor == newColor) {
            return image;
        }
        image[sr][sc] = newColor;
        fillLeft(image, sr - 1, sc, fromColor, newColor);
        fillTop(image, sr, sc - 1, fromColor, newColor);
        fillRight(image, sr + 1, sc, fromColor, newColor);
        fillBottom(image, sr, sc + 1, fromColor, newColor);
        return image;
    }

    private boolean fill(int[][] image, int sr, int sc, int fromColor, int newColor) {
        if (sr >= image.length || sc >= image[0].length
                || sr < 0 || sc < 0) {
            return false;
        }
        if (image[sr][sc] != fromColor) {
            return false;
        }
        image[sr][sc] = newColor;
        return true;
    }

    private void fillRight(int[][] image, int sr, int sc, int fromColor, int newColor) {
        if (!fill(image, sr, sc, fromColor, newColor)) {
            return;
        }
        fillTop(image, sr, sc - 1, fromColor, newColor);
        fillRight(image, sr + 1, sc, fromColor, newColor);
        fillBottom(image, sr, sc + 1, fromColor, newColor);
    }

    private void fillLeft(int[][] image, int sr, int sc, int fromColor, int newColor) {
        if (!fill(image, sr, sc, fromColor, newColor)) {
            return;
        }
        image[sr][sc] = newColor;
        fillLeft(image, sr - 1, sc, fromColor, newColor);
        fillTop(image, sr, sc - 1, fromColor, newColor);
        fillBottom(image, sr, sc + 1, fromColor, newColor);
    }

    private void fillTop(int[][] image, int sr, int sc, int fromColor, int newColor) {
        if (!fill(image, sr, sc, fromColor, newColor)) {
            return;
        }
        image[sr][sc] = newColor;
        fillLeft(image, sr - 1, sc, fromColor, newColor);
        fillTop(image, sr, sc - 1, fromColor, newColor);
        fillRight(image, sr + 1, sc, fromColor, newColor);
    }

    private void fillBottom(int[][] image, int sr, int sc, int fromColor, int newColor) {
        if (!fill(image, sr, sc, fromColor, newColor)) {
            return;
        }
        image[sr][sc] = newColor;
        fillLeft(image, sr - 1, sc, fromColor, newColor);
        fillRight(image, sr + 1, sc, fromColor, newColor);
        fillBottom(image, sr, sc + 1, fromColor, newColor);
    }
}