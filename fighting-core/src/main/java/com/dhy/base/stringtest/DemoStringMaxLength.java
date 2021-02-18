package com.dhy.base.stringtest;

/**
 * @author lvaolin
 * @create 2019/12/17 1:12 PM
 */
public class DemoStringMaxLength {

    public static void main(String[] args) {
        String str = new String("aaaaaaaaa");
        System.out.println(str.length());
        System.out.println(Integer.MAX_VALUE/1024/1024*2+"M");

    }
}
