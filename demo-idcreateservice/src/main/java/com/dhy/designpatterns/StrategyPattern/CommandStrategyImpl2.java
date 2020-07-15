package com.dhy.designpatterns.StrategyPattern;

public class CommandStrategyImpl2 implements ICommandStrategy {
    static {
        CommandStrategyContext.reg(new CommandStrategyImpl2());
    }
    @Override
    public boolean isCan(String flag) {
        return  "2".equals(flag)?true:false;
    }

    @Override
    public void receive(String data) {
        System.out.println(2);
    }

    @Override
    public void response(String data) {
        System.out.println(2);
    }
}
