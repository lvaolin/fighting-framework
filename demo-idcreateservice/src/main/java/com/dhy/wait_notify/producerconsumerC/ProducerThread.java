package com.dhy.wait_notify.producerconsumerC; /**
 * Created by lvaolin on 17/9/6.
 */

/**
 * @author lvaolin
 * @create 17/9/6 下午2:56
 */
public class ProducerThread extends Thread{
    private FactoryUtil factoryUtil;

    public ProducerThread(FactoryUtil factoryUtil){
        this.factoryUtil = factoryUtil;
    }

    @Override
    public void run(){
        while (true) {
            try {
                factoryUtil.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
