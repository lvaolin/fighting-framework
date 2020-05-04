package com.base;

import java.io.IOException;

/**
 *  do while  一定比 while 多执行一次吗？
 */
public class While {


    public static void main(String[] args) {

        int i=5;
        while (--i>0){
            System.out.println(i);
        }

        System.out.println("------------------------------------------");
        i=5;

        do{
            System.out.println(i);
        }while (--i>0);



    }

}
