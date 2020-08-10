package com.dhy.leetcode;

/**
 * 一个数组中有一种数出现了奇数次，其它数都出现了偶数次，找到并打印这种数
 */
public class Zuo2 {

    public static void main(String[] args) {
        int[] a = {200,200,100,200,200};

        System.out.println(find(a));
    }

    /**
     * 思路：N^N=0  N^0=N
     * 异或运算满足交换率和结合率，偶数种类的数异或结果一定是0，所有元素异或的结果一定是那个奇数的值
     * @param arr
     * @return
     */
    public static int find(int[] arr) {
        int eor = 0;
        for (int i = 0; i <arr.length ; i++) {
            eor^=arr[i];
        }
        return eor;
    }
}


