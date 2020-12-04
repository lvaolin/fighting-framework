package com.dhy.java8.functionalinterface;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.test(()->{
            System.out.println("xxxx");
        });

    }

    void test(MyFunInterface myFunInterface){
        System.out.println("befire xxx");
        myFunInterface.print();
        System.out.println("after xxx");
    }
}
