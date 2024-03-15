package com.walklown.learn.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 1466. 重新规划路线
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class Solution231207 {

    public static void main(String[] args) {
        Solution231207 solution = new Solution231207();
        int[][] connections = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        System.out.println(solution.minReorder(4, connections));
    }

    public int minReorder(int n, int[][] connections) {
        Map<Integer, List<Integer>> sourceMap = new HashMap<>();
        Map<Integer, List<Integer>> changeMap = new HashMap<>();
        for (int[] connection : connections) {
            List<Integer> aToList = sourceMap.computeIfAbsent(connection[1], t -> new ArrayList<>());
            aToList.add(connection[0]);
            List<Integer> bToList = changeMap.computeIfAbsent(connection[0], t -> new ArrayList<>());
            bToList.add(connection[1]);
        }
        return findNext(-1, 0, sourceMap, changeMap);
    }

    private int findNext(int pre, int point, Map<Integer, List<Integer>> sourceMap, Map<Integer, List<Integer>> changeMap) {
        int changeNum = 0;
        List<Integer> targetList = sourceMap.get(point);
        if (targetList != null) {
            for (Integer target : targetList) {
                if (target == pre) {
                    continue;
                }
                changeNum += findNext(point, target, sourceMap, changeMap);
            }
        }
        List<Integer> changeTargetList = changeMap.get(point);
        if (changeTargetList != null) {
            for (Integer target : changeTargetList) {
                if (target == pre) {
                    continue;
                }
                changeNum++;
                changeNum += findNext(point, target, sourceMap, changeMap);
            }
        }
        return changeNum;
    }
}
