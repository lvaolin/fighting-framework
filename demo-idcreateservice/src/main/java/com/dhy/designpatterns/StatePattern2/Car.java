package com.dhy.designpatterns.StatePattern2;

public class Car {

    public static void main(String[] args) {
        CarState carState = new CarStateStop();
        carState.close();
        carState.open();
        carState.running();
        carState.stop();

        carState = new CarStateRunning();

        carState.close();
        carState.open();
        carState.running();
        carState.stop();

    }
}
