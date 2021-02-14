package com.dhy.java8.lambda;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;

/**
 * @Title TestSupplier
 * @Description
 * @Author lvaolin
 * @Date 2021/2/14 21:22
 **/
public class TestSupplier {

    public static void main(String[] args) {
        DoubleSupplier doubleSupplier = ()->{
          return 100;
        };

        double asDouble = doubleSupplier.getAsDouble();

        DoubleConsumer doubleConsumer=(d)->{
            System.out.println(2*d);
        };

        doubleConsumer.accept(asDouble);

        BooleanSupplier booleanSupplier = ()->{
          return true;
        };


    }
}
