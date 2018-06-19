package com.zzp.java;

import java.util.ArrayList;
import java.util.List;

public class LambdaTest {

    public static void main(String[] args) {

    }
    private int sumInJava(int size, Sum sum){
        List values = new ArrayList<>();
        values.stream().reduce(size)
    }
    private interface Sum {
        int calc(int a, int b);
    }
}
