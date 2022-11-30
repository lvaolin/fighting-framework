package com.dhy.demo.spring.mybatis.infrastructure.datebase.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Project spring-boot-mybatis
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/11/30 上午8:56
 */
public class DriverManagerProxy {
    public static Map connections = new ConcurrentHashMap();
    public static Map connectionTime = new ConcurrentHashMap();

    public static Connection getConnection(
            String strUrl,
            String strUser,
            String strPassword) throws SQLException {

        Connection connection = DriverManager.getConnection(strUrl, strUser, strPassword);
        connections.put(connection, new Exception());
        connectionTime.put(connection, new java.util.Date());
        System.out.println("****************DataSource Connection  get  size = " + connections.size());
        return connection;

    }

    public static void close(Connection connection){
        if (connection!=null) {
            try {
                connection.close();
                connections.remove(connection);
                connectionTime.remove(connection);
                System.out.println("****************DataSource Connection close size = " + connections.size());

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
