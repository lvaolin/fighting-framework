package com.dhy.designpatterns.StrategyPattern;

public class Test {
    public static void main(String[] args) {
        ICommandStrategy commandStrategy = CommandStrategyContext.getCommandImpl("2");
        if (commandStrategy==null) {
            System.out.println("没有实现此命令的处理");
            return;
        }
        commandStrategy.receive("");
        commandStrategy.response("");
    }
}
