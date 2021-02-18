package com.dhy.designpatterns.StatePattern2;

public class CarStateClose implements CarState {
    @Override
    public void open() {
        System.out.println("open the door");
    }

    @Override
    public void close() {
    }

    @Override
    public void running() {
        System.out.println("running the car");
    }

    @Override
    public void stop() {

    }
}
