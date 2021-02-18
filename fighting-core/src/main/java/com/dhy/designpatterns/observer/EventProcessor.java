package com.dhy.designpatterns.observer;

/**
 * 事件处理接口
 */
public interface EventProcessor {
    public void process(MyEvent myEvent);
}
