package com.dhy.demo.spring.mybatis.infrastructure.datebase.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DBUtil {
	public static Map connections = new ConcurrentHashMap();
	public static Map connectionTime = new ConcurrentHashMap();

	public static  Connection getConnection()  {
		Connection conn = null;
		String strUrl = "jdbc:mysql://localhost:3306/seata_storage?characterEncoding=utf8&serverTimezone=UTC";
		String strUser = "root";
		String strPassword = "root";

		try {
			conn = DriverManager.getConnection(strUrl, strUser, strPassword);
			connections.put(conn, new Exception());
			connectionTime.put(conn, new java.util.Date());
			System.out.println("****************DataSource Connection  get  size = " + connections.size());

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
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
