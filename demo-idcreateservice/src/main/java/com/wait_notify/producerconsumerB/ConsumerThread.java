package com.wait_notify.producerconsumerB; /**
 * Created by lvaolin on 17/9/6.
 */

/**
 * @author lvaolin
 * @create 17/9/6 下午3:10
 */
public class ConsumerThread extends Thread {

    @Override
    public void run(){
        FactoryUtil factoryUtil = new FactoryUtil();
        while (true) {
            try {
                factoryUtil.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
