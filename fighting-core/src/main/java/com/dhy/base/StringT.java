package com.dhy.base;

/**
 * @author lvaolin
 * @create 2019/9/4 7:48 PM
 */
public class StringT {
    static volatile boolean flag = false;
    public static void main(String[] args) {
        flag = true;
        String temp = "hh";
        String s1 = "a" + temp;
        String s2 = "ahh";
        System.out.println(s1 == s2);    // false
    }
}
