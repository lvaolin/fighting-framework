package com.dhy.java8.lambda;

import java.util.function.BiConsumer;

/**
 * @Title TestBiConsumer
 * @Description
 * @Author lvaolin
 * @Date 2021/2/14 21:55
 **/
public class TestBiConsumer {

    public static void main(String[] args) {



        BiConsumer<Integer,Integer> biConsumer = (t,u)->{
            System.out.println(t+u);
        };

        biConsumer.accept(100,200);


    }
}
