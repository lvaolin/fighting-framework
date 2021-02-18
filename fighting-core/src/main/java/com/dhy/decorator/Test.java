package com.dhy.decorator;

/**
 * 利用装饰器模式
 * 获取 鸡蛋香肠手抓饼的价格
 * 获取 香肠鸡蛋手抓饼的价格
 */
public class Test {

    public static void main(String[] args) {
        Bread shreddedBread= new ShreddedBread();

        Bread sausageShreddedBread = new SausageShreddedBread(shreddedBread);

        Bread eggShrededBread = new EggShrededBread(sausageShreddedBread);

        System.out.println(eggShrededBread.getDesc()+eggShrededBread.getPrice());
    }

}
