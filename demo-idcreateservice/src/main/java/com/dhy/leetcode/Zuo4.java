package com.dhy.leetcode;

/**
 * 一个数组中有两种数出现了奇数次，其它数都出现了偶数次，怎么找到并打印这两种数？
 */
public class Zuo4 {

    public static void main(String[] args) {
        int arr[] = {6666,3,3,4,4,5,77777,5,5,77777};
        find(arr);
    }

    /**
        假设要找的数是 a,b，且a不等于b，则a和b的二进制一定有一位是不同的
     有异或运算的特点可知：arr^ = a^b
     eor=a^b !=0
     则eor一定有一位等于1
     最右侧1  int rightOne= eor&(~eor+1)
     int onlyOne=0

     * @param x
     * @return
     */
    public static void find(int[] x) {

        int eor=0;
        for (int i = 0; i <x.length ; i++) {
            eor^=x[i];
        }

        int rightOne = eor&(~eor+1);

        int onlyOne = 0;

        for (int i = 0; i <x.length ; i++) {
            if ((rightOne&x[i])!=0) {//???????
                onlyOne ^=x[i];
            }
        }

        System.out.println(onlyOne+":"+(onlyOne^eor));
    }
}


