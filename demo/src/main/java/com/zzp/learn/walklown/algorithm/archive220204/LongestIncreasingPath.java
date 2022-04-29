package com.zzp.learn.walklown.algorithm.archive220204;

public class LongestIncreasingPath {

    public static void main(String[] args) {
        LongestIncreasingPath obj = new LongestIncreasingPath();
        System.out.println(obj.longestIncreasingPath(new int[][]{{9,9,4}, {6,6,8}, {2,1,1}}));
    }

    private int[][] memo;

    private int[][] matrix;

    private static int[][] skewnesses = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        this.matrix = matrix;
        this.memo = new int[matrix.length][matrix[0].length];
        int result = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result = Math.max(dp(i, j), result);
            }
        }
        return result;
    }

    private int dp(int i, int j) {
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int fromLength = 0;
        for (int[] skewness : skewnesses) {
            int fromI = i + skewness[0];
            int fromJ = j + skewness[1];
            if ((fromI >= 0 && fromI < matrix.length)
            && (fromJ >= 0 && fromJ < matrix[0].length)
            && matrix[fromI][fromJ] < matrix[i][j]) {
                fromLength = Math.max(fromLength, dp(fromI, fromJ));
            }
        }
        return memo[i][j] = fromLength + 1;
    }
}