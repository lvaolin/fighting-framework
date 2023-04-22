package com.dhy.demo.spring.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project spring-boot-jdbc-template
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2023/4/22 下午6:16
 */
public class TestJdbc {
    private static List<Connection> connectionList = new ArrayList<>();
    static {
        // 1.加载并注册JDBC驱动
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws SQLException, IOException {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    insert();
                }
            }).start();
        }

        // 5.关闭连接，释放资源

        System.in.read();
    }

    private static void insert() {
        Connection conn =null;
        try {

            // 2.创建数据库连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/seata_storage?useSSL=false&useUnicode" +
                    "=true" +
                    "&characterEncoding" +
                    "=UTF8&serverTimezone=Asia/Shanghai", "root", "root");
            connectionList.add(conn);
            conn.setAutoCommit(false);
            // 3.创建Statement对象
            PreparedStatement ps = conn.prepareStatement("insert into goods values(?,?,?,?)");
            ps.setString(1,"1111");
            ps.setString(2,"1111");
            ps.setString(3,"1111");
            ps.setString(4,"1111");
            // 4.遍历查询结果
            ps.executeUpdate();
            conn.commit();
        }catch (Exception e){
            //conn.rollback();
            e.printStackTrace();
        }finally {
            try {
                if (conn !=null&&conn.isClosed() ==false) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
