package com.zzp.lamda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class oneTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
//        Iterable<Integer> integers = list;
//        Iterator<Integer> integerIterator = list.iterator();
        int sum = 0;
//        for (Integer item : list){
//            sum += item;
//        }
        list.stream().reduce(sum, Integer::sum);
        System.out.println(sum);
    }
}
