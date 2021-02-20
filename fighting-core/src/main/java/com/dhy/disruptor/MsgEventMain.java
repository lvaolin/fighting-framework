package com.dhy.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * 参照 MQ中间件来理解，disruptor 是单机内存版的MQ
 */
public class MsgEventMain {
    public static void main(String[] args) throws Exception {
        // 1.创建Ring Buffer中事件元素的工厂对象
        MsgEventFactory factory = new MsgEventFactory();
        // 2.指定Ring Buffer的大小,必须为2的幂次方
        int bufferSize = 1024;
        // 3.构造Disruptor
        Disruptor<MsgEvent> disruptor = new Disruptor<MsgEvent>(factory, bufferSize, DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());
        // 4.注册消费者(每一个队列有3个不同职能的消费者，1个持久化消息，1个远程副本复制，1个业务处理消费)
        disruptor.handleEventsWith(new JournalConsumer(), new ReplicationConsumer()).then(new ApplicationConsumer());
        // 5.启动 开始工作--
        disruptor.start();
        // 6.从Disruptor中获取Ring Buffer
        RingBuffer<MsgEvent> ringBuffer = disruptor.getRingBuffer();
        // 7. 创建生产者
        MsgEventProducer<MyDto> producer = new MsgEventProducer(ringBuffer);
        // 8. 生产元素，并放入Ring Buffer
        for (int l = 0; l < 100; l++) {
            producer.onData(new MyDto(l));
            Thread.sleep(1000);
        }
        // 9.挂起当前线程
        //Thread.currentThread().join();
        //10.关闭disruptor和executor
        disruptor.shutdown();

    }
}