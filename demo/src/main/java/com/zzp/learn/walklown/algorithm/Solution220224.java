package com.zzp.learn.walklown.algorithm;

import com.zzp.learn.walklown.json.JacksonUtils;

/**
 * 1706. 球会落何处
 * 用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
 * <p>
 * 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
 * <p>
 * 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
 * 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 * 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，或者被一块挡导向到箱子的任意一侧边上，就会卡住。
 * <p>
 * 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j] 为 1 或 -1
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220224 {

    public static void main(String[] args) {
        Solution220224 solution = new Solution220224();
        int[][] grid = new int[][] {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, -1}};
        int[] result = solution.findBall(grid);
        System.out.println(JacksonUtils.toJSONString(result));
    }

    public int[] findBall(int[][] grid) {
        // 0卡住，-1左，1右
        int[][] points = new int[grid.length][grid[0].length];
        for (int m = 0; m < grid.length; m++) {
            for (int n = 0; n < grid[m].length; n++) {
                if (grid[m][n] == -1) {
                    if (n - 1 >= 0 && grid[m][n - 1] == -1) {
                        points[m][n] = -1;
                    } else {
                        points[m][n] = 0;
                    }
                } else {
                    if (n + 1 <= grid[m].length - 1 && grid[m][n + 1] == 1) {
                        points[m][n] = 1;
                    } else {
                        points[m][n] = 0;
                    }
                }
            }
        }
        int[] result = new int[grid[0].length];

        for (int n = 0; n < grid[0].length; n++) {
            int point = n;
            for (int m = 0; m < grid.length; m++) {
                if (points[m][point] == 0) {
                    point = -1;
                    break;
                } else {
                    point += points[m][point];
                }
            }
            result[n] = point;
        }
        return result;
    }
}
