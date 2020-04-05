package com.dhy.decorator;

/**
 * 鸡蛋手抓饼
 */
public class EggShrededBread extends DeractorBread {

    EggShrededBread(Bread bread) {
        super(bread);
    }

    @Override
    public String getDesc() {
        return "鸡蛋"+super.getDesc();
    }

    @Override
    public int getPrice() {
        return 2+super.getPrice();
    }
}
