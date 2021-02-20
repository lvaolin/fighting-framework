package com.dhy.disruptor;

import com.lmax.disruptor.RingBuffer;
//事件生产者
public class MsgEventProducer<T> {
    private final RingBuffer<MsgEvent> ringBuffer;

    public MsgEventProducer(RingBuffer<MsgEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(T t) {
        long sequence = ringBuffer.next(); // 8.1 第一阶段，获取序列号
        try {
            MsgEvent<T> event = ringBuffer.get(sequence); // 8.2 获取序列号对应的实体元素
            event.set(t); // 8.3 修改元素的值
        } finally {
            ringBuffer.publish(sequence);// 8.4 发布元素
        }
    }
}