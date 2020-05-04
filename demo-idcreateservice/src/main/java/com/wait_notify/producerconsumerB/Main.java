package com.wait_notify.producerconsumerB;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new ConsumerThread().start();
        new ProducerThread().start();
    }
}
