package com.dhy.spi;

import java.util.ServiceLoader;

public class TestRobot {
    public static void main(String[] args) {

        ServiceLoader<Robot> load = ServiceLoader.load(Robot.class);
        load.forEach(Robot::sayHello);

        load.forEach(robot -> {
            System.out.println("test"+System.currentTimeMillis());
        });
    }
}
