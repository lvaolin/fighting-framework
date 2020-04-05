package com.dhy.decorator;

/**
 * 饼装饰器
 */
public class DeractorBread  extends Bread {

    private Bread bread;

    DeractorBread(Bread bread){
        this.bread = bread;
    }
    /**
     * 得到手抓饼的描述
     * @return
     */
    @Override
    public  String getDesc(){
        return bread.getDesc();
    }

    /**
     * 得到手抓饼价格
     * @return
     */
    @Override
    public  int getPrice(){
        return bread.getPrice();
    }
}
