package com.dhy.jvm;

/**
 * 内存溢出测试
 *
 * @author lvaolin
 * @create 2019/12/26 4:24 PM
 */
public class OOM01_01 {

    private static byte[][] arr;

    public static void main(String[] args){
        for(int i=0;i<12;i++){
            arr = new byte[1024][1024];
        }
    }
}
