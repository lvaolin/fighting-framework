package com.dhy.decorator;


/**
 * 饼
 */
public abstract class Bread {

    /**
     * 得到饼的配料
     * @return
     */
    public abstract String getDesc();

    /**
     * 得到饼价格
     * @return
     */
    public abstract  int getPrice();
}
