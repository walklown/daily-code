package com.zzp.learn.walklown.algorithm.archive220204;

class Solution200823 {

    public static void main(String[] args) {
        Solution200823 solution = new Solution200823();
        System.out.println(solution.rangeBitwiseAnd(3, 4));
    }

    public int rangeBitwiseAnd(int m, int n) {
        int result = m;
        if (m == n) {
            return result;
        }
        int cat = n ^ m;
        int bit = 0;
        while (cat > 0) {
            cat = cat / 2;
            bit ++;
            result = result / 2;
        }
        for (int i = 0; i< bit; i++) {
            result = result * 2;
        }
        return result;
    }
}