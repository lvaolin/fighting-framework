package com.dhy.java8.lambda;

import com.google.common.base.Function;

import java.util.Comparator;
import java.util.function.BiFunction;

public class TestLamda1 {
    public String name;
    public int age;

    public TestLamda1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello(){
        System.out.println("hello,i am "+name+",age "+age);
    }
    public static void main(String[] args) {

        Function<String, Integer> stringIntegerFunction = Integer::parseInt;
        Integer apply = stringIntegerFunction.apply("1000");
        System.out.println(apply);

        Function<Integer, Integer> add = TestLamda1::add;
        int compare = add.apply(2);
        System.out.println(compare);

        sayAdd(TestLamda1::add, 1);


        BiFunction<String, Integer, TestLamda1> aNew = TestLamda1::new;
        TestLamda1 nnnnnn = aNew.apply("nnnnnn", 99);
        nnnnnn.sayHello();
    }

    public static Integer sayAdd(java.util.function.Function<Integer, Integer> function, Integer a) {
        Integer apply = function.apply(a);
        System.out.println(apply);
        return apply;

    }

    public static int add(int a) {
        return a * 100;
    }
}
