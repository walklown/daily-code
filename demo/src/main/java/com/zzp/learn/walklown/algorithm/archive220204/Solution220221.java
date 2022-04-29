package com.zzp.learn.walklown.algorithm.archive220204;

/**
 * 838. 推多米诺
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * <p>
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * <p>
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * <p>
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * <p>
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * <p>
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 *
 * @author 守愚（张智沛）
 * @date 2022-02-17
 */
class Solution220221 {

    public static void main(String[] args) {
        Solution220221 solution = new Solution220221();
        System.out.println(solution.pushDominoes(".L.R...LR..L.."));
    }

    public String pushDominoes(String dominoes) {
        Character left = 'L';
        char right = 'R';
        dominoes = dominoes + right;
        char[] chars = dominoes.toCharArray();
        char last = left;
        for (int i = 0; i < chars.length; i++) {
            if (Character.valueOf('.').equals(chars[i])) {
                int offset = i;
                for (; i < chars.length; i++) {
                    if (!Character.valueOf('.').equals(chars[i])) {
                        if (left.equals(last)) {
                            if (left.equals(chars[i])) {
                                for (int j = offset; j < i; j++) {
                                    chars[j] = left;
                                }
                            }
                        } else {
                            if (left.equals(chars[i])) {
                                int length = i - offset;
                                int half = length / 2;
                                int mid = offset + half;
                                for (; offset < mid; offset++) {
                                    chars[offset] = right;
                                }
                                if (length % 2 == 1) {
                                    offset = offset + 1;
                                }
                                for (; offset < i; offset++) {
                                    chars[offset] = left;
                                }
                            } else {
                                for (int j = offset; j < i; j++) {
                                    chars[j] = right;
                                }
                            }
                        }
                        last = chars[i];
                        break;
                    }
                }
            } else {
                last = chars[i];
            }
        }
        return String.valueOf(chars, 0, chars.length - 1);
    }
}
