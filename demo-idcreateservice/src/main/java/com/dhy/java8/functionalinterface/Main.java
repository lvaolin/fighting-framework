package com.dhy.java8.functionalinterface;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.test((String s)-> s+"hello");
        main.test((String x)->{
            return x+"world";
        });


    }

    void test(MyFunInterface myFunInterface){
        System.out.println("befire xxx");
        System.out.println(myFunInterface.print("大哥"));
        System.out.println("after xxx");
    }
}
