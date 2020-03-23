package com.dhy.spi;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.ServiceLoader;

public class Test2 {

    public static void main(String[] args) throws SQLException {

        String url="1xxxxx";
        String user="root";
        String pwd = "root";

        ServiceLoader<MyDriver> serviceLoader = ServiceLoader.load(MyDriver.class);
        System.out.println("加载所有实现类：");
        for (MyDriver driverService: serviceLoader){
            System.out.println(driverService.getClass().getName());
            driverService.getConnection(url,user,pwd);
        }







    }

}
