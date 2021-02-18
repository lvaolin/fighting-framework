package com.dhy.hashcode;

/**
 * equal 方法相等的 对象 的hashcode一定相等
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("aaa".hashCode());

        User a = new User();
        User b = new User();

        System.out.println("a.com.dhy.hashcode:"+a.hashCode());
        System.out.println("b.com.dhy.hashcode:"+b.hashCode());
        //System.out.println(a==b);
        System.out.println(a.equals(b));




    }

}
