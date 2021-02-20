package com.dhy.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Project fighting
 * @Description 事件工厂
 * @Author lvaolin
 * @Date 2021/2/20 10:28 上午
 */
public class MsgEventFactory implements EventFactory<MsgEvent> {
    @Override
    public MsgEvent newInstance() {
        return new MsgEvent();
    }
}
