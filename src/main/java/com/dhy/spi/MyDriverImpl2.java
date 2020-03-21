package com.dhy.spi;

public class MyDriverImpl2 implements MyDriver {
    @Override
    public void getConnection(String url, String user, String pwd) {
        System.out.println("得到了MyDriverImpl2 的connection");
    }
}
