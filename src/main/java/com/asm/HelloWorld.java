package com.asm;

public class HelloWorld {
    public void sayHello() {
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
}
