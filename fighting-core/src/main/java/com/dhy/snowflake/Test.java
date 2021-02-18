package com.dhy.snowflake;

/**
 * 测试雪花算法
 *
 * @author lvaolin
 * @create 2020/1/13 10:37 AM
 */
public class Test {

    public static void main(String[] args) {
        IOidService oidService = new OidService();
        System.out.println(oidService.generateObjectID());
    }
}
