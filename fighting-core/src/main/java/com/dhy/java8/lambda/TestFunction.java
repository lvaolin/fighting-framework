package com.dhy.java8.lambda;

import java.util.function.Function;

/**
 * @Title TestFunction
 * @Description
 * @Author lvaolin
 * @Date 2021/2/14 22:02
 **/
public class TestFunction {

    public static void main(String[] args) {

        Function<String,String> function = (s)->{
            return s+s;
        };

        System.out.println(function.apply("hello function "));
    }
}
