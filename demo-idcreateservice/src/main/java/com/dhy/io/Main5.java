package com.dhy.io;

import java.util.*;
public class Main5{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        boolean current = true;//当前输入为 总个数
        int totalCount = 0;//总个数
        Set<Integer> set = new TreeSet<>(new Comparator<Integer>(){
            public int compare(Integer i1,Integer i2){
                return i1-i2;
            }
        });
        while(scanner.hasNextInt()){
            if(current){
                totalCount = scanner.nextInt();//个数
                current = false;
            }else{
                int x = scanner.nextInt();
                if(!set.contains(x)){
                    set.add(x);
                }

                totalCount--;
                if(totalCount==0){
                    current = true;
                    set.stream().forEach((i)->{
                        System.out.println(i);
                    });
                }


            }
        }
    }
}