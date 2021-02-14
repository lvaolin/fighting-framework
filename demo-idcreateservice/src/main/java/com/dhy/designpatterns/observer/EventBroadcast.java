package com.dhy.designpatterns.observer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 事件广播器
 */
public class EventBroadcast {
    public  static void broadcast(List<EventProcessor> list,MyEvent event){

        CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                for (EventProcessor eventProcessor : list) {
                    eventProcessor.process(event);
                }
            }
        }).thenRunAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"=广播完毕="+event.name());
            }
        });
    }
}
