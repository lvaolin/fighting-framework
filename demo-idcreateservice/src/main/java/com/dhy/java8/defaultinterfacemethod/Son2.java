package com.dhy.java8.defaultinterfacemethod;

public class Son2 implements Father,Mather {

    public static void main(String[] args) {
        new Son2().eat();
    }

    @Override
    public void eat() {
        Mather.super.eat();
    }

}
