package com.dhy.lock;

/**
 * @author lvaolin
 * @create 2019/12/25 3:49 PM
 */
public class JUC02_1 {

    public static void main(String[] args){
        JUC02_1_demo atomicDemo = new JUC02_1_demo();
        for (int x = 0;x < 100; x++){
            new Thread(atomicDemo).start();
        }
    }

}
