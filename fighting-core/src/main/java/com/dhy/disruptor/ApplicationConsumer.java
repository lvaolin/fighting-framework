package com.dhy.disruptor;

import com.lmax.disruptor.EventHandler;

//真正处理业务逻辑的消费者
public class ApplicationConsumer implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println(Thread.currentThread().getName() + "Application Event: " + event.get());
    }
}