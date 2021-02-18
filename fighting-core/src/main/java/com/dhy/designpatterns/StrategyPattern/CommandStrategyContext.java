package com.dhy.designpatterns.StrategyPattern;

import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CommandStrategyContext {
    private static List<ICommandStrategy> list=new CopyOnWriteArrayList<>();
    public static void reg(ICommandStrategy commandStrategy){
        list.add(commandStrategy);
    }


    @SneakyThrows
    public static ICommandStrategy getCommandImpl(String flag){
        Class.forName("com.dhy.designpatterns.StrategyPattern.CommandStrategyImpl1");
        for (ICommandStrategy commandStrategy : list) {
            if (commandStrategy.isCan(flag)) {
                return commandStrategy;
            }
        }
        return null;
    }
}
