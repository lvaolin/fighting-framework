package com.dhy.disruptor;

import com.lmax.disruptor.EventHandler;

//将输入数据写入持久性日志文件的消费者
public class JournalConsumer implements EventHandler<LongEvent> {
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) {
        System.out.println(Thread.currentThread().getName() + "Persist Event: " + event.get());
    }
}