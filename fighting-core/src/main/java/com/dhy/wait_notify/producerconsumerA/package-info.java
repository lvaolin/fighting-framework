/**
 * Created by lvaolin on 17/9/7.
 */
package com.dhy.wait_notify.producerconsumerA;
/**
 * A方案问题：消费者和生产者线程同一时刻只能有一个在运行
 *因为生产者和消费者共同使用 buf对象锁 ，这个锁一个时刻只有一个线程可以获得
 * @author lvaolin
 * @create 17/9/7  上午10:24
 */