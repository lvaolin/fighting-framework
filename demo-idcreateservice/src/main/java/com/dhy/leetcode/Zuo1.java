package com.dhy.leetcode;

/**
 * 如何不用额外变量交换两个数
 */
public class Zuo1 {

    public static void main(String[] args) {
        int[] a = {100,200};
        a[0]=a[0]^a[1];
        a[1]=a[0]^a[1];//y=x^y^y=x^0=x
        a[0]=a[0]^a[1];
        System.out.println(a[0]);
        System.out.println(a[1]);
    }

    public static void swap(int x, int y) {
        x=x^y;
        y=x^y;//y=x^y^y=x^0=x
        x=x^y;//x=x^y^x=y^0=y
        System.out.println(x);
        System.out.println(y);
    }
}


