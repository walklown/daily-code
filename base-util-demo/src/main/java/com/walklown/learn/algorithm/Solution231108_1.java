package com.walklown.learn.algorithm;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LCR 111. 除法求值
 * 中等
 * 51
 * 相关企业
 * 给定一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * 注意：输入总是有效的。可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * 提示：
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 *
 * @author Walklown
 */
public class Solution231108_1 {

    public static void main(String[] args) {
        Solution231108_1 solution = new Solution231108_1();
        List<List<String>> equations = new ArrayList<>();
        List<String> subList = new ArrayList<>();
        subList.add("a");
        subList.add("e");
        equations.add(subList);
        List<String> subList2 = new ArrayList<>();
        subList2.add("b");
        subList2.add("e");
        equations.add(subList2);

        List<List<String>> queries = new ArrayList<>();
        List<String> subList1 = new ArrayList<>();
        subList1.add("a");
        subList1.add("b");
        queries.add(subList1);
        System.out.println(Arrays.toString(solution.calcEquation(equations, new double[] {4.0, 3.0}, queries)));
    }


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // equations key排序
        Map<String, Map<String, Double>> keyMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> equation = equations.get(i);
            Map<String, Double> key1Map0 = keyMap.computeIfAbsent(equation.get(0), t -> new HashMap<>());
            Map<String, Double> key1Map1 = keyMap.computeIfAbsent(equation.get(1), t -> new HashMap<>());
            List<Map.Entry<String, Double>> value0List = new ArrayList<>(key1Map0.entrySet());
            List<Map.Entry<String, Double>> value1List = new ArrayList<>(key1Map1.entrySet());
            for (Map.Entry<String, Double> entry0 : value0List) {
                for (Map.Entry<String, Double> entry1 : value1List) {
                    // 0的连接点x 连接到 1的连接点y，值为 1/y * 0/1 / 0/x = x/y
                    double a = values[i] * entry1.getValue() / entry0.getValue();
                    Map<String, Double> key1MapX = keyMap.computeIfAbsent(entry0.getKey(), t -> new HashMap<>());
                    key1MapX.put(entry1.getKey(), a);
                    // 1的连接点y 连接到 0的连接点x，值为 y/x
                    Map<String, Double> key1MapY = keyMap.computeIfAbsent(entry1.getKey(), t -> new HashMap<>());
                    key1MapY.put(entry0.getKey(), 1 / a);
                }
            }
            // 0 -> 1的连接点y
            for (Map.Entry<String, Double> entry1 : value1List) {
                // 0 连接到 1的连接点y，值为 1/y * 0/1 = 0/y
                double a = values[i] * entry1.getValue();
                key1Map0.put(entry1.getKey(), a);
                // 1的连接点y 连接到 0
                Map<String, Double> key1MapY = keyMap.computeIfAbsent(entry1.getKey(), t -> new HashMap<>());
                key1MapY.put(equation.get(0), 1 / a);
            }
            // 0的连接点x -> 1
            for (Map.Entry<String, Double> entry0 : value0List) {
                // 0的连接点x 连接到 1，值为 0/1 / 0/x = x/1
                double a = values[i] / entry0.getValue();
                Map<String, Double> key1MapX = keyMap.computeIfAbsent(entry0.getKey(), t -> new HashMap<>());
                key1MapX.put(equation.get(1), a);
                // 1 连接到 0的连接点x
                key1Map1.put(entry0.getKey(), 1 / a);
            }
            // 0 -> 1
            key1Map0.put(equation.get(1), values[i]);
            // 1 -> 0
            key1Map1.put(equation.get(0), 1 / values[i]);
        }
        double[] resultArray = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            if (query.get(0).equals(query.get(1))) {
                if (keyMap.containsKey(query.get(0))) {
                    resultArray[i] = 1.0;
                } else {
                    resultArray[i] = -1.0;
                }
                continue;
            }
            Double result = null;
            Map<String, Double> key1Map = keyMap.get(query.get(0));
            if (key1Map != null) {
                result = key1Map.get(query.get(1));
            }
            if (result == null) {
                resultArray[i] = -1.0;
            } else {
                resultArray[i] = result;
            }
        }
        return resultArray;
    }
}
