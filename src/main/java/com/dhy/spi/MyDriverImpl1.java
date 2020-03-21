package com.dhy.spi;

public class MyDriverImpl1 implements MyDriver {
    @Override
    public void getConnection(String url, String user, String pwd) {
        if(isUrlRight(url)){
            System.out.println("得到了MyDriverImpl1 的connection");
        }else{
            System.out.println("不是我的url");
        }

    }

    @Override
    public boolean isUrlRight(String url) {
        return url.startsWith("1")?true:false;
    }
}
