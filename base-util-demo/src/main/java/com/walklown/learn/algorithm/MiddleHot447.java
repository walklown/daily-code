package com.walklown.learn.algorithm;

import com.walklown.learn.json.JacksonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 447. 回旋镖的数量
 *
 * @author @author <a href="mailto:warlklown@gmail.com">Walklown<a/>
 */
public class MiddleHot447 {

    public static void main(String[] args) {
        MiddleHot447 middleHot = new MiddleHot447();
        System.out.println(middleHot.numberOfBoomerangs(new int[][] {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}}));
    }

    public int numberOfBoomerangs(int[][] points) {
        if (points.length < 3) {
            return 0;
        }
        int total = 0;
        int[][] length = new int[points.length][points.length];
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int one;
                if (points[i][0] > points[j][0]) {
                    one = points[i][0] - points[j][0];
                } else {
                    one = points[j][0] - points[i][0];
                }
                int two;
                if (points[i][1] > points[j][1]) {
                    two = points[i][1] - points[j][1];
                } else {
                    two = points[j][1] - points[i][1];
                }
                int num = one * one + two * two;
                length[i][j] = num;
                for (int k = 0; k < i; k++) {
                    if (num == length[k][i]) {
                        total += 2;
                    }
                    if (num == length[k][j]) {
                        total += 2;
                    }
                }
                for (int k = i + 1; k < j; k++) {
                    if (num == length[i][k]) {
                        total += 2;
                    }
                    if (num == length[k][j]) {
                        total += 2;
                    }
                }
            }
        }
        return total;
    }

}
