package com.walklown.learn.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 2646. 最小化旅行的价格总和
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class Solution231206 {

    public static void main(String[] args) {
        Solution231206 solution = new Solution231206();
        int[][] edges = {{0, 1}, {1, 2}, {1, 3}};
        int[] price = {2, 2, 10, 6};
        int[][] trips = {{0, 3}, {2, 1}, {2, 3}};
        System.out.println(solution.minimumTotalPrice(4, edges, price, trips));
    }

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        // 构建路径
        Map<Integer, List<Integer>> path = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            List<Integer> aToList = path.computeIfAbsent(edges[i][0], t -> new LinkedList<>());
            aToList.add(edges[i][1]);
            List<Integer> bToList = path.computeIfAbsent(edges[i][1], t -> new LinkedList<>());
            bToList.add(edges[i][0]);
        }
        // 节点计数
        int[] num = new int[n];
        for (int[] trip : trips) {
            if (trip[0] == trip[1]) {
                num[trip[0]]++;
                continue;
            }
            findPath(-1, trip[0], trip[1], path, num);
        }
        // 规划减半节点
        return tryCountNext(false, -1 ,0, price, path, num);
    }

    private boolean findPath(int pre, int from, int to, Map<Integer, List<Integer>> path, int[] num) {
        List<Integer> middleList = path.get(from);
        if (middleList == null) {
            return false;
        }
        Integer findPoint = null;
        for (Integer point : middleList) {
            if (point == pre) {
                continue;
            }
            if (point == to) {
                num[point]++;
                findPoint = from;
                break;
            }
            if (findPath(from, point, to, path, num)) {
                findPoint = from;
                break;
            }
        }
        if (findPoint != null) {
            num[from]++;
            return true;
        }
        return false;
    }

    private int tryCountNext(boolean preHalf, int pre, int i, int[] price, Map<Integer, List<Integer>> path, int[] num) {
        List<Integer> middleList = path.get(i);
        // 不减半
        int pointTotalPrice = price[i] * num[i];
        if (middleList != null) {
            int nextTotalPrice = 0;
            for (Integer point : middleList) {
                if (point == pre) {
                    continue;
                }
                pointTotalPrice += tryCountNext(false, i, point, price, path, num);
            }
        }
        if (!preHalf && pointTotalPrice > 0) {
            int tryHalfPointTotalPrice = price[i] * num[i] / 2;
            // 尝试减半
            if (middleList != null) {
                for (Integer point : middleList) {
                    if (point == pre) {
                        continue;
                    }
                    tryHalfPointTotalPrice += tryCountNext(true, i, point, price, path, num);
                }
            }
            if (tryHalfPointTotalPrice < pointTotalPrice) {
                pointTotalPrice = tryHalfPointTotalPrice;
            }
        }
        return pointTotalPrice;
    }
}
