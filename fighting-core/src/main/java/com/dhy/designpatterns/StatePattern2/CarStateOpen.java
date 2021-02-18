package com.dhy.designpatterns.StatePattern2;

public class CarStateOpen implements CarState {
    @Override
    public void open() {

    }

    @Override
    public void close() {
        System.out.println("close the door");
    }

    @Override
    public void running() {

    }

    @Override
    public void stop() {

    }
}
