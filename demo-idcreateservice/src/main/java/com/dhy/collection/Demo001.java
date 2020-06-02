package com.dhy.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 求两个集合的交集
 * 可以用treeset 或者  hashset
 */
public class Demo001 {

    public static void main(String[] args) {

        Set setA = new TreeSet();
        for (int i = 0; i <10000 ; i++) {
            setA.add(i);
        }

        Set setB = new TreeSet();
        for (int i = 0; i <20 ; i++) {
            setB.add(i*i*i*i);

        }

        long start = System.currentTimeMillis();
        for (Object o : setB) {
            if (setA.contains(o)) {
                System.out.println(o);
            }

        }

        long stop = System.currentTimeMillis();
        System.out.println("耗时："+(stop - start));
    }
}
