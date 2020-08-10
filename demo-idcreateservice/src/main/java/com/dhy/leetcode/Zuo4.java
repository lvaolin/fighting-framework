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
        //eor=a^b
        //a!=b!=0  -> a,b 一定有一位置或者多个位置是不相等的
        //eor!=0   eor 最右位置第一个1一定是不相等的那个位置之一，算出来
        int rightOne = eor&(~eor+1);

        //我们以 rightOne位置为标准将数组分成两大类，一类是 那个位置为1的，另一类是为0的
        //0000 0100
        //1101 0100
        //1101 0100
        //0101 0100

        int onlyOne = 0;

//        for (int i = 0; i <x.length ; i++) {
//            if ((rightOne&x[i])!=0) {//???????
//                onlyOne ^=x[i];
//            }
//        }

        for (int i = 0; i <x.length ; i++) {
            onlyOne = rightOne^x[i];
        }


        System.out.println(onlyOne+":"+(onlyOne^eor));
    }
}


