package com.dhy.java8.stream;

import java.util.*;

public class ParallelStream {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        integers.stream().forEach((num)-> System.out.println(num));
        integers.parallelStream().forEach((num)->{
            System.out.println(Thread.currentThread().getName()+"@"+num);
        });

    }
}
