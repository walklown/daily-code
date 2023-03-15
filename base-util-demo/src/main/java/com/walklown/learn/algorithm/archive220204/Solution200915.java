package com.walklown.learn.algorithm.archive220204;

/**
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution200915 {

    public static void main(String[] args) {
        Solution200915 solution = new Solution200915();
        char[][] board = new char[][] {
                {'5', '3', SPACE, SPACE, '7', SPACE, SPACE, SPACE, SPACE},
                {'6', SPACE, SPACE, '1', '9', '5', SPACE, SPACE, SPACE},
                {SPACE, '9', '8', SPACE, SPACE, SPACE, SPACE, '6', SPACE},
                {'8', SPACE, SPACE, SPACE, '6', SPACE, SPACE, SPACE, '3'},
                {'4', SPACE, SPACE, '8', SPACE, '3', SPACE, SPACE, '1'},
                {'7', SPACE, SPACE, SPACE, '2', SPACE, SPACE, SPACE, '6'},
                {SPACE, '6', SPACE, SPACE, SPACE, SPACE, '2', '8', SPACE},
                {SPACE, SPACE, SPACE, '4', '1', '9', SPACE, SPACE, '5'},
                {SPACE, SPACE, SPACE, SPACE, '8', SPACE, SPACE, '7', '9'}
        };
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.printf(board[i][j] + ",");
            }
            System.out.println();
        }
        solution.solveSudoku(board);
        System.out.println("\n\n");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.printf(board[i][j] + ",");
            }
            System.out.println();
        }
    }

    private boolean ifEnd = false;

    private static final char SPACE = '.';

    public void solveSudoku(char[][] board) {
        int[][] temp = new int[9][9];
        // 坐标对应第几个九宫格
        int[][] helper = new int[][] {
                {0, 0, 0, 1, 1, 1, 2, 2, 2},
                {0, 0, 0, 1, 1, 1, 2, 2, 2},
                {0, 0, 0, 1, 1, 1, 2, 2, 2},
                {3, 3, 3, 4, 4, 4, 5, 5, 5},
                {3, 3, 3, 4, 4, 4, 5, 5, 5},
                {3, 3, 3, 4, 4, 4, 5, 5, 5},
                {6, 6, 6, 7, 7, 7, 8, 8, 8},
                {6, 6, 6, 7, 7, 7, 8, 8, 8},
                {6, 6, 6, 7, 7, 7, 8, 8, 8}};
        // [行][数字]
        boolean[][] line = new boolean[9][9];
        // [列序列][数字]
        boolean[][] column = new boolean[9][9];
        // [九宫格序列][数字]
        boolean[][] salve = new boolean[9][9];
        // 未填格数
        int space = 81;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (SPACE == board[i][j]) {
                    continue;
                }
                space--;
                int n = board[i][j] - '1';
                temp[i][j] = n;
                line[i][n] = true;
                column[j][n] = true;
                salve[helper[i][j]][n] = true;
            }
        }
        space = helper(0, 0, temp, line, column, salve, helper, space, board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == '.') {
                    board[i][j] = (char) (temp[i][j] + '1');
                }
            }
        }
    }

    public int helper(int l, int c, int[][] temp, boolean[][] line, boolean[][] column, boolean[][] salve, int[][] helper, int space,
                      char[][] board) {
        //游戏初始已经有值的格子跳过
        if (board[l][c] != SPACE) {
            if (c < 8) {
                //一行未到头 向右填充
                return helper(l, c + 1, temp, line, column, salve, helper, space, board);
            } else {
                //一行到头进入下一行
                return helper(l + 1, 0, temp, line, column, salve, helper, space, board);
            }
        } else {
            int minusSpace = space - 1;
            for (int v = 0; v < 9; v++) {
                //存在任意冲突则跳过当前数字
                if (line[l][v] || column[c][v] || salve[helper[l][c]][v]) {
                    continue;
                }
                temp[l][c] = v;
                if (0 == minusSpace) {
                    ifEnd = true;
                    return minusSpace;
                }
                line[l][v] = true;
                column[c][v] = true;
                salve[helper[l][c]][v] = true;
                if (c < 8) {
                    //一行未到头 向右填充
                    minusSpace = helper(l, c + 1, temp, line, column, salve, helper, minusSpace, board);
                } else {
                    //一行到头进入下一行
                    minusSpace = helper(l + 1, 0, temp, line, column, salve, helper, minusSpace, board);
                }
                if (ifEnd) {
                    return minusSpace;
                }
                line[l][v] = false;
                column[c][v] = false;
                salve[helper[l][c]][v] = false;
            }
            return space;
        }
    }
}