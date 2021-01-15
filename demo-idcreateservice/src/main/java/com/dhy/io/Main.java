package com.dhy.io;

import java.io.*;
import java.util.*;

/**
 * 间隔2个数删除一个数
 */
public class Main {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int n;
        while (scanner.hasNextInt()) {
            n = scanner.nextInt();
            if(n<=0) System.out.print(0);
            if(n>1000) n = 999;
            int[] array = new int[n];
            System.out.print(deleteNumber(-1,array));
        }


    }

    public static int deleteNumber(int beginIndex,int[] n){
        int deleteCount = 0;
        while(true){
            beginIndex = (beginIndex+3)%n.length;
            if(n[beginIndex]!=-1){
                n[beginIndex]=-1;
                deleteCount++;
                if(deleteCount==n.length){
                    return beginIndex;
                }
            }
        }

    }

}