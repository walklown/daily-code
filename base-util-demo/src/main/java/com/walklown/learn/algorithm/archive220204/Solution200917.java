package com.walklown.learn.algorithm.archive220204;


import java.util.LinkedList;
import java.util.List;

class Solution200917 {

    public static void main(String[] args) {
        int[][] edges = new int[][] {{1,2}, {2,3}, {3,4}, {4,1}, {1,5}};
        Solution200917 solution = new Solution200917();
        int[] a = solution.findRedundantDirectedConnection(edges);
        for (int i : a) {
            System.out.println(i);
        }
    }

    public int[] findRedundantDirectedConnection(int[][] edges) {
        boolean[] temp = new boolean[edges.length];
        boolean[] roots = new boolean[edges.length];
        List<int[]> errors = new LinkedList<>();
        int rootNum = 0;
        for (int[] edge : edges) {
            if (!temp[edge[1] - 1]) {
                temp[edge[1] - 1] = true;
            } else {
                // 多header，异常
                return edge;
            }
            if (roots[edge[1] - 1]) {
                if (rootNum <= 1) {
                    // 异常
                    errors.add(edge);
                }
                // 如果标记为root，去除标记
                roots[edge[1] - 1] = false;
                rootNum--;
            }
            if (!temp[edge[0] - 1]) {
                // 在尾部出现过就肯定不是root
                roots[edge[0] - 1] = true;
                rootNum++;
            }
        }
        return errors.get(errors.size() - 1);
    }
}
