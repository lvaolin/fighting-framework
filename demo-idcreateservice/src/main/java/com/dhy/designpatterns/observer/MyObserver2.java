package com.dhy.designpatterns.observer;

/**
 * 观察者2
 */
public class MyObserver2 implements EventProcessor {
    @Override
    public void process(MyEvent myEvent) {
        System.out.println("MyObserver2:"+myEvent.toString());
    }
}
