package com.dhy.base;

/**
 * @author lvaolin
 * @create 2019/9/4 2:10 PM
 */
public class StringInternTest {

    /**
     * == 用来比较内存地址的   equals 用来比较内容的
     * @param args
     */
    public static void main(String[] args) {
        String str1 = new String("abc");//产生的对象位于堆内存中
        String str2 = "abc"; //产生的对象唯一 方法区常量池中
        String str3 = "a" + "b" + "c"; //编译器会优化为 str3 = "abc"
        //地址不一样 false
        System.out.println(str1==str2);
        //intern的意思是：当前字符串的值如果已经存在于方法区的常量池中则直接返回池中地址，如果不存在则加入常量池中并发挥常量池地址
        //两个返回的都是常量池地址，输出true
        System.out.println(str1.intern() == str2);
        //由于编译器的优化str3相当于 "abc" , 输出true，
        System.out.println(str2 == str3);
    }
}
