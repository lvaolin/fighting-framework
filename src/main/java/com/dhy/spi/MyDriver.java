package com.dhy.spi;

public interface MyDriver {
   void getConnection(String url,String user,String pwd);

   boolean isUrlRight(String url);
}
