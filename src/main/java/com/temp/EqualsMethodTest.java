package com.temp;

import java.util.HashMap;

/**
 * == 比较的是内存引用地址    equals比较的是对象内容
 * @author lvaolin
 * @create 2019/12/31 1:15 PM
 */
public class EqualsMethodTest {


    public static void main(String[] args) {
        String a1 = new String("a");
        String a2 = new String("a");
        String a3 = "a";
        //false
        System.out.println(a1==a2);
        //true
        //String的equals方法是经过重写的，规则：先比较 引用是否相等，如果相等直接true
        //如果不等，接着比较内容时候一样。
        //Object 的equals比较的是对象的物理地址是否相同，所以 如果不是String 等 基本类型的包装类的话要自己重写 equals方法

        System.out.println(a1.equals(a2));
        //false
        System.out.println(a3 == a1);
        //true
        System.out.println(a3.equals(a1));

        int b1 = 1;
        int b2 = 1;

        Integer b3 = new Integer(1);
        Integer b4 = new Integer(1);
        //true
        System.out.println(b1==b2);
        //true
        System.out.println(b3.equals(b1));
        //true 自动拆箱
        System.out.println(b3 == b1);

        b3.hashCode();

        HashMap hashMap = new HashMap();
        hashMap.put("","");
        hashMap.hashCode();
        Object o = new Object();
        o.hashCode();
    }
}
