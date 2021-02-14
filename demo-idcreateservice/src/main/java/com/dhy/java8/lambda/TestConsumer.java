package com.dhy.java8.lambda;

import java.util.function.Consumer;

/**
 * @Title TestConsumer
 * @Description  测试消费者接口
 * @Author lvaolin
 * @Date 2021/2/14 21:04
 **/
public class TestConsumer {

    public static void main(String[] args) {
        //Consumer 特点是可以接收一个参数，无返回值
        Consumer<String> consumer1 =(a)->{
            System.out.println(a);
        } ;

        Consumer<String> consumer2 =(a)->{
            System.out.println(a+a);
        } ;
        //下面这句决定了执行顺序 ： 先执行consumer1.accept  再执行consumer2.accept
        //consumer1.andThen(consumer2);
        //下面两句必须有
        //consumer1.accept(" hello ");
        //consumer2.accept(" java8");
        //下面这句决定了执行顺序 ： 先执行consumer2.accept  再执行consumer1.accept
        consumer2.andThen(consumer1);

        //下面两句必须有
        consumer2.accept(" java8");
        consumer1.accept(" hello ");


    }
}
