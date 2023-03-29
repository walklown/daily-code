package com.walklown.learn.jarkata.base;

import java.util.ArrayList;
import java.util.List;

public class TypeInferenceDemo {
    public static void main(String[] args) {
        // Java earlier versions
        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(100);
        System.out.println(numbers);
        // Java 7 version
        numbers = new ArrayList<>();
        numbers.add(101);
        System.out.println(numbers);
        // Java 8 version
        fill(new ArrayList<>());
    }

    private static void fill(List<Integer> list) {
        list.add(102);
        System.out.println(list);
    }
}