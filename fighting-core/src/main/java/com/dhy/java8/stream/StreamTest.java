package com.dhy.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Project fighting
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2021/3/5 10:05 上午
 */
public class StreamTest {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("lv1", "lv2", "z1", "z2");

        List<String> collect = list.parallelStream().filter((e) -> {
            return e.startsWith("lv");
        }).map((e) -> {
            return e.replaceAll("lv", "dahuangya");
        }).collect(Collectors.toList());

        collect.stream().forEach((e)->{
            System.out.println(e);
        });

    }
}
