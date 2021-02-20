package com.dhy.disruptor;

/**
 * @Project fighting
 * @Description 事件元素，其实就是消息
 * @Author lvaolin
 * @Date 2021/2/20 10:27 上午
 */
public class MsgEvent<T> {
    private T value;
    public void set(T value) {
        this.value = value;
    }
    public T get() {
        return value;
    }
}
