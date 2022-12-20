package com.zzp.learn.walklown.algorithm;

import java.util.Arrays;

/**
 * 1697. 检查边长度限制的路径是否存在
 * 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示点 ui 和点 vi 之间有一条长度为 disi 的边。请注意，两个点之间可能有 超过一条边 。
 *
 * 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] ，判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
 *
 * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结果为 true 时， answer 第 j 个值为 true ，否则为 false 。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * 输出：[false,true]
 * 解释：上图为给定的输入数据。注意到 0 和 1 之间有两条重边，分别为 2 和 16 。
 * 对于第一个查询，0 和 1 之间没有小于 2 的边，所以我们返回 false 。
 * 对于第二个查询，有一条路径（0 -> 1 -> 2）两条边都小于 5 ，所以这个查询我们返回 true 。
 * 示例 2：
 *
 *
 * 输入：n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * 输出：[true,false]
 * 解释：上图为给定数据。
 *
 *
 * 提示：
 *
 * 2 <= n <= 105
 * 1 <= edgeList.length, queries.length <= 105
 * edgeList[i].length == 3
 * queries[j].length == 3
 * 0 <= ui, vi, pj, qj <= n - 1
 * ui != vi
 * pj != qj
 * 1 <= disi, limitj <= 109
 * 两个点之间可能有 多条 边。
 */
class Solution221214 {

    public static void main(String[] args) {
        Solution221214 solution = new Solution221214();
        System.out.println(Arrays.toString(solution.distanceLimitedPathsExist(13,
                new int[][] {{9,1,53},{3,2,66},{12,5,99},{9,7,26},{1,4,78},{11,1,62},{3,10,50},{12,1,71},{12,6,63},{1,10,63},{9,10,88},{9,11,59},{1,4,37},{4,2,63},{0,2,26},{6,12,98},{9,11,99},{4,5,40},{2,8,25},{4,2,35},{8,10,9},{11,9,25},{10,11,11},{7,6,89},{2,4,99},{10,4,63}},
                new int[][] {{9,7,65},{9,6,1},{4,5,34},{10,8,43},{3,7,76},{4,2,15},{7,6,52},{2,0,50},{7,6,62},{1,0,81},{4,5,35},{0,11,86},{12,5,50},{11,2,2},{9,5,6},{12,0,95},{10,6,9},{9,4,73},{6,10,48},{12,0,91},{9,10,58},{9,8,73},{2,3,44},{7,11,83},{5,3,14},{6,2,33}})));
    }

    private int[][] map;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        this.map = new int[n][n];
        for (int[] edge : edgeList) {
            int x;
            int y;
            // x较小
            if (edge[0] < edge[1]) {
                x = edge[0];
                y = edge[1];
            } else {
                x = edge[1];
                y = edge[0];
            }
            if (map[x][y] == 0 || map[x][y] > edge[2]) {
                map[x][y] = edge[2];
            }
        }
        for (int x = 0; x < n - 1; x++) {
            for (int y = x + 1; y < n; y++) {
                for (int i = 0; i < x; i++) {
                    // 处理x的链接
                    if (map[i][x] != 0) {
                        int max = Math.max(map[i][x], map[x][y]);
                        if (map[i][y] == 0) {
                            map[i][y] = max;
                        } else if (map[i][y] > max){
                            map[i][y] = max;
                        }
                    }
                    // 处理y的链接
                    if (map[i][y] != 0) {
                        int max = Math.max(map[i][y], map[x][y]);
                        if (map[i][x] == 0) {
                            map[i][x] = max;
                        } else if (map[i][x] > max){
                            map[i][x] = max;
                        }
                    }
                }
                for (int i = x + 1; i < y; i++) {
                    // 处理x的链接
                    if (map[x][i] != 0) {
                        int max = Math.max(map[x][i], map[x][y]);
                        if (map[i][y] == 0) {
                            map[i][y] = max;
                        } else if (map[i][y] > max){
                            map[i][y] = max;
                        }
                    }
                    // 处理y的链接
                    if (map[i][y] != 0) {
                        int max = Math.max(map[i][y], map[x][y]);
                        if (map[x][i] == 0) {
                            map[x][i] = max;
                        } else if (map[x][i] > max){
                            map[x][i] = max;
                        }
                    }
                }
                for (int i = y + 1; i < n; i++) {
                    // 处理x的链接
                    if (map[x][i] != 0) {
                        int max = Math.max(map[x][i], map[x][y]);
                        if (map[y][i] == 0) {
                            map[y][i] = max;
                        } else if (map[y][i] > max){
                            map[y][i] = max;
                        }
                    }
                    // 处理y的链接
                    if (map[y][i] != 0) {
                        int max = Math.max(map[y][i], map[x][y]);
                        if (map[x][i] == 0) {
                            map[x][i] = max;
                        } else if (map[x][i] > max){
                            map[x][i] = max;
                        }
                    }
                }
            }
        }
        boolean[] result = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int x;
            int y;
            if (query[0] < query[1]) {
                x = query[0];
                y = query[1];
            } else {
                x = query[1];
                y = query[0];
            }
            result[i] = map[x][y] != 0 && map[x][y] < query[2];
        }
        return result;
    }
}
