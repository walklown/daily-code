package com.zzp.learn.walklown.algorithm;

/**
 * N x N 的棋盘 board 上，按从 1 到 N*N 的数字给方格编号，编号 从左下角开始，每一行交替方向。
 *
 * 例如，一块 6 x 6 大小的棋盘，编号如下：
 *
 *
 * r 行 c 列的棋盘，按前述方法编号，棋盘格中可能存在 “蛇” 或 “梯子”；如果 board[r][c] != -1，那个蛇或梯子的目的地将会是 board[r][c]。
 *
 * 玩家从棋盘上的方格 1 （总是在最后一行、第一列）开始出发。
 *
 * 每一回合，玩家需要从当前方格 x 开始出发，按下述要求前进：
 *
 * 选定目标方格：选择从编号 x+1，x+2，x+3，x+4，x+5，或者 x+6 的方格中选出一个目标方格 s ，目标方格的编号 <= N*N。
 * 该选择模拟了掷骰子的情景，无论棋盘大小如何，你的目的地范围也只能处于区间 [x+1, x+6] 之间。
 * 传送玩家：如果目标方格 S 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 S。 
 * 注意，玩家在每回合的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，你也不会继续移动。
 *
 * 返回达到方格 N*N 所需的最少移动次数，如果不可能，则返回 -1。
 *
 *  
 *
 * 示例：
 *
 * 输入：[
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,35,-1,-1,13,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,15,-1,-1,-1,-1]]
 * 输出：4
 * 解释：
 * 首先，从方格 1 [第 5 行，第 0 列] 开始。
 * 你决定移动到方格 2，并必须爬过梯子移动到到方格 15。
 * 然后你决定移动到方格 17 [第 3 行，第 5 列]，必须爬过蛇到方格 13。
 * 然后你决定移动到方格 14，且必须通过梯子移动到方格 35。
 * 然后你决定移动到方格 36, 游戏结束。
 * 可以证明你需要至少 4 次移动才能到达第 N*N 个方格，所以答案是 4。
 *  
 *
 * 提示：
 *
 * 2 <= board.length = board[0].length <= 20
 * board[i][j] 介于 1 和 N*N 之间或者等于 -1。
 * 编号为 1 的方格上没有蛇或梯子。
 * 编号为 N*N 的方格上没有蛇或梯子。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/snakes-and-ladders
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution210627 {

    public static void main(String[] args) {
        Solution210627 solution210613 = new Solution210627();
        System.out.println(solution210613.guessNumber(10));
    }

    public int guessNumber(int n) {
        if (n == 1) {
            return n;
        }
        int left = 1;
        int right = n;
        while (true) {
            int middle = left + (right - left) / 2;
            int compareResult = guess(middle);
            if (compareResult == 0) {
                left = middle;
                break;
            }
            if (compareResult < 0) {
                right = middle;
            } else {
                left = left == middle ? left + 1 : middle;
            }
        }
        return left;
    }

    int guess(int num) {
        return Integer.compare(8, num);
    }
}
