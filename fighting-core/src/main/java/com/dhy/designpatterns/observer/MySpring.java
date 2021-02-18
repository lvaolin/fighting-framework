package com.dhy.designpatterns.observer;

import java.util.ArrayList;

/**
 * 模拟spring容器 启动流程 产生事件
 */
public class MySpring {

    private static ArrayList<EventProcessor> eventProcessors = new ArrayList<>();
    {
        eventProcessors.add(new MyObserver1());
        eventProcessors.add(new MyObserver2());
    }
    public static void main(String[] args) {
        MySpring mySpring = new MySpring();
        starting();
        started();
        stoping();
        stoped();

        for (;;);
    }

    public static void starting(){
        EventBroadcast.broadcast(eventProcessors,MyEvent.starting);
        //do something other
    }

    public static void started(){
        EventBroadcast.broadcast(eventProcessors,MyEvent.started);
        //do something other
    }

    public static void stoping(){
        EventBroadcast.broadcast(eventProcessors,MyEvent.stoping);
        //do something other
    }

    public static void stoped(){
        EventBroadcast.broadcast(eventProcessors,MyEvent.stoped);
        //do something other
    }
}
