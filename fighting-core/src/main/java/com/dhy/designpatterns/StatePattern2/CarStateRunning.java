package com.dhy.designpatterns.StatePattern2;

public class CarStateRunning implements CarState {
    @Override
    public void open() {
    }

    @Override
    public void close() {
    }

    @Override
    public void running() {
    }

    @Override
    public void stop() {
        System.out.println("stop the car");
    }
}
