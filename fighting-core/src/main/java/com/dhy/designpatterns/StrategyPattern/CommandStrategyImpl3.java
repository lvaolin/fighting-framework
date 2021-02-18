package com.dhy.designpatterns.StrategyPattern;

public class CommandStrategyImpl3 implements ICommandStrategy {
    static {
        CommandStrategyContext.reg(new CommandStrategyImpl3());
    }
    @Override
    public boolean isCan(String flag) {
        return  "3".equals(flag)?true:false;
    }

    @Override
    public void receive(String data) {
        System.out.println(3);
    }

    @Override
    public void response(String data) {
        System.out.println(3);
    }
}
