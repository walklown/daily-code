package com.walklown.learn.algorithm.archive220204;

/**
 * 688. 骑士在棋盘上的概率
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * <p>
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * <p>
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * <p>
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 * <p>
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220217 {

    public static void main(String[] args) {
        Solution220217 solution = new Solution220217();
        System.out.println(solution.knightProbability(3, 3, 0, 0));
    }

    public double knightProbability(int n, int k, int row, int column) {
        int total = 1;
        for (int i = 0; i < k; i++) {
            total *= 8;
        }
        int out = getOut(n, k, row, column);
        return (double) (total - out) / total;
    }

    public int getOut(int n, int k, int row, int column) {
        if (k == 0) {
            return 0;
        }
        // 左1
        int left1 = row - 1;
        int left2 = row - 2;
        int right1 = row + 1;
        int right2 = row + 2;

        int currentOut = 0;
        if (left1 < 0) {
            // 两个出
            currentOut += kToOut(k) * 2 * 2;
        } else if (left2 < 0) {
            // 一个出
            currentOut += kToOut(k) * 2;
            currentOut += topBottom(n, k, left1, column, 2);
        } else {
            // 零个出
            currentOut += topBottom(n, k, left2, column, 1);
            currentOut += topBottom(n, k, left1, column, 2);
        }
        if (right1 > n - 1) {
            // 两个出
            currentOut += kToOut(k) * 2 * 2;
        } else if (right2 > n - 1) {
            // 一个出
            currentOut += kToOut(k) * 2;
            currentOut += topBottom(n, k, right1, column, 2);
        } else {
            // 零个出
            currentOut += topBottom(n, k, right2, column, 1);
            currentOut += topBottom(n, k, right1, column, 2);
        }
        return currentOut;
    }

    private int kToOut(int k) {
        int num = 1;
        for (int i = 0; i < k - 1; i++) {
            num *= 8;
        }
        return num;
    }

    private int topBottom(int n, int k, int row, int column, int columMoveNum) {
        int top = column - columMoveNum;
        int bottom = column + columMoveNum;
        int currentOut = 0;
        if (top < 0) {
            // 一个出
            currentOut += kToOut(k);
        } else {
            // 左1-上2
            currentOut += getOut(n, k - 1, row, top);
        }
        if (bottom >= n) {
            // 一个出
            currentOut += kToOut(k);
        } else {
            // 左1-下2
            currentOut += getOut(n, k - 1, row, bottom);
        }
        return currentOut;
    }
}