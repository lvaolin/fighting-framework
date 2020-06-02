package com.dhy.wait_notify.producerconsumerB; /**
 * Created by lvaolin on 17/9/6.
 */

/**
 * @author lvaolin
 * @create 17/9/6 下午2:56
 */
public class ProducerThread extends Thread{

    @Override
    public void run(){
        FactoryUtil factoryUtil = new FactoryUtil();
        while (true) {
            try {
                factoryUtil.put(new Object());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
