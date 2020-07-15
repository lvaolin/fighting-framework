package com.dhy.designpatterns.StrategyPattern;

public interface ICommandStrategy {
    /**
     * 是否合适处理
     * @return
     */
    boolean isCan(String flag);

    /**
     * 接收处理数据
     */
    default void receive(String data) {

    }

    /**
     * 响应数据
     * @param data
     */
    default void response(String data){

    }
}
