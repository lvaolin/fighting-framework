package com.dhy.json;

import java.math.BigDecimal;

/**
 * 判断class是否为8种基本数据类型和void类型
 */
public class Test02 {
    public static void main(String[] args) {
        // 基本类型判断

        System.out.println(int.class.isPrimitive());
        System.out.println(Integer.class.isPrimitive());
        System.out.println(Integer.class.isSynthetic());
        System.out.println(void.class.isPrimitive());
        System.out.println(BigDecimal.class.isPrimitive());
        System.out.println(Class.class.isPrimitive());

    }
}
