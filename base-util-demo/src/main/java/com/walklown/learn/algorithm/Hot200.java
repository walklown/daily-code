package com.walklown.learn.algorithm;

/**
 * 200. 岛屿数量
 *
 * @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class Hot200 {

    public static void main(String[] args) {
        Hot200 hot = new Hot200();
        int num = hot.numIslands(new char[][]{
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        });
        System.out.println(num);
    }

    public int numIslands(char[][] grid) {
        int num = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '0') {
                    continue;
                }
                num++;
                fromLeft(i, j, grid);
            }
        }
        return num;
    }

    private void fromTop(int i, int j, char[][] grid) {
        grid[i][j] = '0';
        // bottom
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            fromTop(i + 1, j, grid);
        }
        // right
        if (j + 1 < grid[i].length && grid[i][j + 1] == '1') {
            fromLeft(i, j + 1, grid);
        }
        // left
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            fromRight(i, j - 1, grid);
        }
    }

    private void fromBottom(int i, int j, char[][] grid) {
        grid[i][j] = '0';
        // top
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            fromBottom(i - 1, j, grid);
        }
        // right
        if (j + 1 < grid[i].length && grid[i][j + 1] == '1') {
            fromLeft(i, j + 1, grid);
        }
        // left
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            fromRight(i, j - 1, grid);
        }
    }

    private void fromLeft(int i, int j, char[][] grid) {
        grid[i][j] = '0';
        // top
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            fromBottom(i - 1, j, grid);
        }
        // bottom
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            fromTop(i + 1, j, grid);
        }
        // right
        if (j + 1 < grid[i].length && grid[i][j + 1] == '1') {
            fromLeft(i, j + 1, grid);
        }
    }

    private void fromRight(int i, int j, char[][] grid) {
        grid[i][j] = '0';
        // top
        if (i - 1 >= 0 && grid[i - 1][j] == '1') {
            fromBottom(i - 1, j, grid);
        }
        // bottom
        if (i + 1 < grid.length && grid[i + 1][j] == '1') {
            fromTop(i + 1, j, grid);
        }
        // left
        if (j - 1 >= 0 && grid[i][j - 1] == '1') {
            fromRight(i, j - 1, grid);
        }
    }
}
