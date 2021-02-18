package com.dhy.leetcode;

/**
 * 如何不用额外变量交换两个数
 */
public class Zuo1 {

    public static void main(String[] args) {
        int[] a = {100,300,200};
        swap(a,2,0);
        System.out.println(a[0]);
        System.out.println(a[2]);
    }

    public static void swap(int[] arr,int x, int y) {
        arr[x]=arr[x]^arr[y];
        arr[y]=arr[x]^arr[y];
        arr[x]=arr[x]^arr[y];
    }
}


