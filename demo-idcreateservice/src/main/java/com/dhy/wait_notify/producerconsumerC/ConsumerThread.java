package com.dhy.wait_notify.producerconsumerC; /**
 * Created by lvaolin on 17/9/6.
 */

/**
 * @author lvaolin
 * @create 17/9/6 下午3:10
 */
public class ConsumerThread extends Thread {

    private FactoryUtil factoryUtil;

    public ConsumerThread(FactoryUtil factoryUtil){
        this.factoryUtil = factoryUtil;
    }

    @Override
    public void run(){
        while (true) {
            try {
                factoryUtil.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
