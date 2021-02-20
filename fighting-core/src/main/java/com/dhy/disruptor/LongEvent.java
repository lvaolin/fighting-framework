package com.dhy.disruptor;

/**
 * @Project fighting
 * @Description 事件元素，其实就是消息
 * @Author lvaolin
 * @Date 2021/2/20 10:27 上午
 */
public class LongEvent {
    private long value;
    public void set(long value) {
        this.value = value;
    }
    public long get() {
        return value;
    }
}
