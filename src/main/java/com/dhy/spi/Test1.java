package com.dhy.spi;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ServiceLoader;

public class Test1 {

    public static void main(String[] args) throws SQLException {

        ServiceLoader<Driver> serviceLoader = ServiceLoader.load(Driver.class);
        for (Driver driverService: serviceLoader){
            System.out.println(driverService.getClass().getName());
        }



    }

}
