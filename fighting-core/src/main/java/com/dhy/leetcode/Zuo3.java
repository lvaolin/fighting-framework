package com.dhy.leetcode;

/**
 * 怎么把一个int类型的数，提取出其二进制最右侧的1来
 */
public class Zuo3 {

    public static void main(String[] args) {
        System.out.println(find(6));
    }

    /**
     * x= 0001 0001 0001
     * ~x=1110 1110 1110
     * ~x+1=1110 1110 1111
     * x&(~x+1) = 0000 0000 0001
     * @param x
     * @return
     */
    public static int find(int x) {
        return x&(~x+1);
    }
}


