package com.dhy.disruptor;

import com.lmax.disruptor.EventHandler;

//将输入数据发送到另一台机器以确保存在数据的远程副本的消费者
public class ReplicationConsumer implements EventHandler<MsgEvent> {
    public void onEvent(MsgEvent event, long sequence, boolean endOfBatch) {
        System.out.println(Thread.currentThread().getName() + "Replication Event: " + event.get());
    }
}