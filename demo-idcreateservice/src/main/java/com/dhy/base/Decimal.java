package com.dhy.base;

import java.math.BigDecimal;

public class Decimal {

    public static void main(String[] args) {
        BigDecimal cost1=new BigDecimal("1024");
        BigDecimal cost2=new BigDecimal("2048");
        BigDecimal cost3=new BigDecimal("3072");
        BigDecimal cost4=new BigDecimal("4096");
        BigDecimal total=BigDecimal.ZERO;
        total=total.add(cost1);
        total=total.add(cost2);
        total=total.add(cost3);
        total=total.add(cost4);
        System.out.println(total);
    }
}
