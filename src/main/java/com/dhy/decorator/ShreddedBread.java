package com.dhy.decorator;

/**
 * 手抓饼
 */
public class ShreddedBread extends  Bread {

    /**
     * 得到手抓饼的描述
     * @return
     */
    @Override
    public String getDesc(){
        return "手抓饼";
    }

    /**
     * 得到手抓饼价格
     * @return
     */
    @Override
    public  int getPrice(){
        return  5;
    }
}
