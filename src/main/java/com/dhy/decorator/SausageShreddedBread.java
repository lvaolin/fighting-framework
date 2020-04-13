package com.dhy.decorator;

/**
 * 香肠手抓饼
 */
public class SausageShreddedBread extends DeractorBread {

    SausageShreddedBread(Bread bread) {
        super(bread);
    }

    @Override
    public String getDesc() {
        return "香肠"+super.getDesc();
    }

    @Override
    public int getPrice() {
        return 3+super.getPrice();
    }
}
