package com.dhy.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @Title TestSuppliner
 * @Description
 * @Author lvaolin
 * @Date 2021/2/14 21:15
 **/
public class TestSuppliner {

    public static void main(String[] args) {

        //提供者
        Supplier<List<String>> supplier = ()->{
            ArrayList<String> strings = new ArrayList<>();
            strings.add("1");
            strings.add("2");
            strings.add("3");
            strings.add("4");
            strings.add("5");
            return strings;
        };
        List<String> strings = supplier.get();
        //消费者
        strings.parallelStream().forEach((x)->{
            System.out.println(x);
        });
    }
}
