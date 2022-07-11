package com.zzp.learn.walklown.algorithm;

import java.util.LinkedList;

/**
 * 871. 最低加油次数
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 * <p>
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
 * <p>
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * <p>
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * <p>
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * <p>
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 1, startFuel = 1, stations = []
 * 输出：0
 * 解释：我们可以在不加油的情况下到达目的地。
 * 示例 2：
 * <p>
 * 输入：target = 100, startFuel = 1, stations = [[10,100]]
 * 输出：-1
 * 解释：我们无法抵达目的地，甚至无法到达第一个加油站。
 * 示例 3：
 * <p>
 * 输入：target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * 输出：2
 * 解释：
 * 我们出发时有 10 升燃料。
 * 我们开车来到距起点 10 英里处的加油站，消耗 10 升燃料。将汽油从 0 升加到 60 升。
 * 然后，我们从 10 英里处的加油站开到 60 英里处的加油站（消耗 50 升燃料），
 * 并将汽油从 10 升加到 50 升。然后我们开车抵达目的地。
 * 我们沿途在1两个加油站停靠，所以返回 2 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= target, startFuel, stations[i][1] <= 10^9
 * 0 <= stations.length <= 500
 * 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 *
 * @author walklown
 * @date 2020/8/11 22:42
 */
class Solution220614 {

    public static void main(String[] args) {
        Solution220614 solution = new Solution220614();
        System.out.println(solution.minRefuelStops(1000000, 80302,
                new int[][] {{9722, 23398}, {78556, 67812}, {149743, 29312}, {152556, 9013}, {187886, 75011}, {229228, 63429},
                        {233251, 25476}, {295384, 34619}, {295898, 33279}, {307369, 14278}, {327240, 63675}, {385401, 60621},
                        {412073, 63923}, {412654, 14329}, {419693, 29492}, {482094, 54481}, {538608, 7938}, {636358, 60898},
                        {655509, 27276}, {722209, 101}, {759411, 27652}, {788104, 73232}, {788748, 29128}, {874117, 25606},
                        {970283, 9019}}));
    }

    private int stationOffset = 0;

    private int[][] stations = null;

    private LinkedList<Integer> fuelList = new LinkedList<>();

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        this.stations = stations;
        int fuel = startFuel;
        int time = 0;
        for (; ; ) {
            if (stationOffset < stations.length) {
                if (fuel < stations[stationOffset][0]) {
                    int length = fuelList.size();
                    for (int i = 0; i < length; i++) {
                        fuel += fuelList.remove(0);
                        time++;
                        if (fuel >= stations[stationOffset][0]) {
                            break;
                        }
                    }
                    if (fuel < stations[stationOffset][0]) {
                        return -1;
                    }
                }
                // 继续加油
                initFuel(fuel);
            } else if (fuel < target) {
                int length = fuelList.size();
                for (int i = 0; i < length; i++) {
                    fuel += fuelList.remove(0);
                    time++;
                    if (fuel >= target) {
                        return time;
                    }
                }
                return -1;
            } else {
                return time;
            }
        }
    }

    private void initFuel(int fuel) {
        for (; stationOffset < stations.length; stationOffset++) {
            if (stations[stationOffset][0] > fuel) {
                break;
            }
            int i = 0;
            for (; i < fuelList.size(); i++) {
                if (fuelList.get(i) < stations[stationOffset][1]) {
                    fuelList.add(i, stations[stationOffset][1]);
                    break;
                }
            }
            if (i == fuelList.size()) {
                fuelList.addLast(stations[stationOffset][1]);
            }
        }
    }
}
