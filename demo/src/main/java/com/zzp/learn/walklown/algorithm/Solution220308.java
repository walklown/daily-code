package com.zzp.learn.walklown.algorithm;

/**
 * 2055. 蜡烛之间的盘子
 * 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 *
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 *
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 *
 *
 *
 * 示例 1:
 *
 * ex-1
 *
 * 输入：s = "**|**|***|", queries = [[2,5],[5,9]]
 * 输出：[2,3]
 * 解释：
 * - queries[0] 有两个盘子在蜡烛之间。
 * - queries[1] 有三个盘子在蜡烛之间。
 * 示例 2:
 *
 * ex-2
 *
 * 输入：s = "***|**|*****|**||**|*", queries = [[1,17],[4,5],[14,17],[5,11],[15,16]]
 * 输出：[9,0,0,0,0]
 * 解释：
 * - queries[0] 有 9 个盘子在蜡烛之间。
 * - 另一个查询没有盘子在蜡烛之间。
 *
 *
 * 提示：
 *
 * 3 <= s.length <= 105
 * s 只包含字符 '*' 和 '|' 。
 * 1 <= queries.length <= 105
 * queries[i].length == 2
 * 0 <= lefti <= righti < s.length
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220308 {

    public static void main(String[] args) {
        Solution220308 solution = new Solution220308();
        int[] result = solution.platesBetweenCandles("**|**|***|", new int[][]{{2, 5}, {5, 9}});
        for (int i : result) {
            System.out.println(i);
        }
    }

    private int[] candles;

    private int length;

    public int[] platesBetweenCandles(String s, int[][] queries) {
        candles = new int[s.length()];
        length = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '|') {
                candles[length] = i;
                length++;
            }
        }
        int[] result = new int[queries.length];
        if (length == 0) {
            return result;
        }
        for (int i = 0; i < queries.length; i++) {
            int[] fromOffsets = splitFindNextOffset(0, length - 1, queries[i][0]);
            int[] toOffsets = splitFindNextOffset(fromOffsets[1], length - 1, queries[i][1]);
            if (toOffsets[0] > fromOffsets[1]) {
                result[i] = candles[toOffsets[0]] - candles[fromOffsets[1]] - 1 - (toOffsets[0] - fromOffsets[1] - 1);
            } else {
                result[i] = 0;
            }
        }
        return result;
    }

    private int[] splitFindNextOffset(int from, int to, int value) {
        if (from == to) {
            return new int[]{from, to};
        }
        int mid = (to - from) / 2 + from;
        if (candles[mid] == value) {
            return new int[]{mid, mid};
        } else if (candles[mid] > value) {
            return splitFindNextOffset(from, mid, value);
        } else {
            if (candles[mid + 1] > value) {
                return new int[]{mid, mid + 1};
            } else {
                return splitFindNextOffset(mid + 1, to, value);
            }
        }
    }
}
