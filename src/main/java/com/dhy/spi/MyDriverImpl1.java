package com.dhy.spi;

public class MyDriverImpl1 implements MyDriver {
    @Override
    public void getConnection(String url, String user, String pwd) {
        System.out.println("得到了MyDriverImpl1 的connection");
    }
}
