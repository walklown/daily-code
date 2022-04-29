package com.zzp.learn.walklown.algorithm.archive220204;

public class MaximalSquare {

    public static void main(String[] args) {
        char[][] matrix = new char[4][5];
        matrix[0] = new char[]{'1', '0', '1', '0', '0'};
        matrix[1] = new char[]{'1', '0', '1', '1', '1'};
        matrix[2] = new char[]{'1', '1', '1', '1', '1'};
        matrix[3] = new char[]{'1', '0', '0', '1', '0'};
        MaximalSquare maximalSquare = new MaximalSquare();
        System.out.println(maximalSquare.maximalSquare(matrix));
    }

    // dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1

    private int maxSize = 0;

    private Integer[][] sizes;

    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        sizes = new Integer[matrix.length][matrix[0].length];
        countSize(matrix, 0, 0);
        return maxSize * maxSize;
    }

    public int countSize(char[][] matrix, int i, int j) {
        if (i >= matrix.length || j >= matrix[0].length) {
            return 0;
        }
        if (sizes[i][j] != null) {
            return sizes[i][j];
        }
        int rightSize = countSize(matrix, i + 1, j);
        int bottomSize = countSize(matrix, i, j + 1);
        int angleSize = countSize(matrix, i + 1, j + 1);
        int size = 0;
        if (matrix[i][j] != '0') {
            size = Math.min(Math.min(rightSize, bottomSize), angleSize) + 1;
            if (size > this.maxSize) {
                this.maxSize = size;
            }
        }
        sizes[i][j] = size;
        return size;
    }
}
