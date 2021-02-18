package com.dhy.wait_notify.producerconsumerA;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        new ConsumerThread().start();
        new ProducerThread().start();
    }
}
