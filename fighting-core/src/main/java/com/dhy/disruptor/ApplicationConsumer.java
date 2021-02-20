package com.dhy.disruptor;

import com.lmax.disruptor.EventHandler;

//真正处理业务逻辑的消费者
public class ApplicationConsumer<T> implements EventHandler<T> {
    public void onEvent(T event, long sequence, boolean endOfBatch) {
        MsgEvent<MyDto>event1 = (MsgEvent<MyDto>) event;
        System.out.println(Thread.currentThread().getName() + "Application Event: " + event1.get().toString());
    }
}