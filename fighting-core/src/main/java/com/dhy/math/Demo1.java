package com.dhy.math;

/**
 * 牛顿迭代法 求平方根
 * 公式：
 */
public class Demo1 {
    public static void main(String[] args) {
        mySqrt(932112111);
    }

    private static double mySqrt(int num) {
        double x0 = num;

        double delta = 1e-12;
        int count = 0;
        while(x0*x0-num>delta) {
            count++;
            x0 = (x0*x0+num) / (2*x0);
        }
        System.out.println(count);
        System.out.println(Math.round(x0*1000)/1000.0);
        return x0;//保留三位小数
    }
}
