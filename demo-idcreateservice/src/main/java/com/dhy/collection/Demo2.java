package com.dhy.collection;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Demo2 {

    public static void main(String[] args) {
        List<Integer> queue1 = new LinkedList<Integer>();
        queue1.add(4);
        queue1.add(2);
        queue1.add(3);
        int[][] res = new int[3][queue1.size()];

        int[] ints = queue1.stream().mapToInt((v) -> {
            return v.intValue();
        }).toArray();

        res[0] = ints;
        System.out.println(res[0][0]);
        System.out.println(res[0][1]);
        System.out.println(res[0][2]);
    }
}
