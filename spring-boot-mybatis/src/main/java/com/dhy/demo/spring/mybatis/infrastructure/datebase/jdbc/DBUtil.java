package com.dhy.demo.spring.mybatis.infrastructure.datebase.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DBUtil {


	public static  Connection getConnection()  {
		Connection conn = null;
		String strUrl = "jdbc:mysql://localhost:3306/seata_storage?characterEncoding=utf8&serverTimezone=UTC";
		String strUser = "root";
		String strPassword = "root";

		try {
			conn = DriverManagerProxy.getConnection(strUrl, strUser, strPassword);

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection connection){
		if (connection!=null) {
			try {
				//connection.close();
				DriverManagerProxy.close(connection);

			} catch (Exception throwables) {
				throwables.printStackTrace();
			}
		}
	}


}
