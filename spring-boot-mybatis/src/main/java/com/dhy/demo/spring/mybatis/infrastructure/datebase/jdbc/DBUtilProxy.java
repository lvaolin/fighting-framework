package com.dhy.demo.spring.mybatis.infrastructure.datebase.jdbc;

import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Project spring-boot-mybatis
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/11/30 上午8:48
 */
public class DBUtilProxy {
    public static Map connections = new ConcurrentHashMap();
    public static Map connectionTime = new ConcurrentHashMap();
    public static Connection getConnection(){
        Connection conn = DBUtil.getConnection();
        connections.put(conn, new Exception());
        connectionTime.put(conn, new java.util.Date());
        System.out.println("****************DataSource Connection  get  size = " + connections.size());

        return conn;
    };

    public static void close(Connection connection){
        DBUtil.close(connection);
        connections.remove(connection);
        connectionTime.remove(connection);
        System.out.println("****************DataSource Connection close size = " + connections.size());

    }
}
