package com.dhy.lock;

/**
 * @author lvaolin
 * @create 2019/12/25 3:56 PM
 */
public class JUC02_1_demo implements Runnable{

    private  int i = 0;
    public int getI(){
        for (int j = 0; j <100000 ; j++) {
            i++;
        }
        return i;
    }
    @Override
    public void run() {

        System.out.println(getI());
    }
}
