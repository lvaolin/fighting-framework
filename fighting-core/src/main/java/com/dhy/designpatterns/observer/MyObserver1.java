package com.dhy.designpatterns.observer;

/**
 * 观察者1
 */
public class MyObserver1 implements EventProcessor {
    @Override
    public void process(MyEvent myEvent) {
        System.out.println("MyObserver1:"+myEvent.toString());
    }
}
