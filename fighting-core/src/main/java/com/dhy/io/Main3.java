package com.dhy.io;

import java.io.*;
import java.util.*;
public class Main3 {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String n;
        while (scanner.hasNextLine()) {
            n = scanner.nextLine();
            printNoSame(n);

        }


    }

    public static void printNoSame(String str){
        Set<Character> set = new HashSet<>();
        char[] arr = str.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(!set.contains(arr[i])){
                System.out.print(arr[i]);
                set.add(arr[i]);
            }
        }
    }



}