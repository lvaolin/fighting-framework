package com.dhy.designpatterns.FactoryMethodPattern;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
        }
        for (String object : list) {
            System.out.println(object);
        }

        LinkedList<String> strings = new LinkedList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        Iterator<String> iterator1 = strings.iterator();
        while (iterator1.hasNext()) {
            String next = iterator1.next();
            System.out.println(next);
        }
    }
}
