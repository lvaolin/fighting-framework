package com;

import sun.misc.Unsafe;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lvaolin
 * @create 18/4/17 上午11:03
 */
public class Test {


    public static void main(String[] args) {


        HashMap<Object, Object> hashMap = new HashMap<>();

        ConcurrentHashMap<Object, Object> chm = new ConcurrentHashMap<>();

        chm.put("","");
        Unsafe.getUnsafe();

    }



}
