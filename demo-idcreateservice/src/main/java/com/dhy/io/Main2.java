package com.dhy.io;

import java.util.Scanner;

/**
 * 间隔2个数删除一个数
 */
public class Main2 {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String n;
        while (scanner.hasNextLine()) {
            n = scanner.nextLine();
            System.out.println(n);
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