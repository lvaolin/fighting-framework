package com.dhy.base;

/**
 * @author lvaolin
 * @create 2019/10/29 7:27 PM
 */
public class SyncTest {

    int a = 1;
    volatile boolean flag = false;

    public  synchronized void testReOrder(){
        int a = 1;
        int b = 1;
        SyncTest test = new SyncTest();
    }

}
