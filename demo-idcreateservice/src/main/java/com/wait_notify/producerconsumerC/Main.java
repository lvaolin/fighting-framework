package com.wait_notify.producerconsumerC;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        FactoryUtil factoryUtil = new FactoryUtil();


        new ProducerThread(factoryUtil).start();

        new ConsumerThread(factoryUtil).start();
        new ConsumerThread(factoryUtil).start();

    }
}
