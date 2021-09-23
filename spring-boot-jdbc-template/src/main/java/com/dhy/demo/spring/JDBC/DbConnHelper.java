package com.dhy.demo.spring.JDBC;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.List;

/**
 * 数据库连接助手
 */
public class DbConnHelper {
    private static final Logger log = LogManager.getLogger(DbConnHelper.class);
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行sql
     *
     * @param host
     * @param port
     * @param userName
     * @param password
     * @param sqlList
     */
    public static void executeSql(String host, String port, String userName, String password, List<String> sqlList) {
        String url = "jdbc:mysql://" + host + ":" + port + "/retl?useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true";
        Connection connection = getConnection(url, userName, password);
        if (connection==null||sqlList==null||sqlList.size()==0) {
            return;
        }
        executeUpdate(connection, sqlList, null);
    }

    // 获取数据库连接
    public static Connection getConnection(String url, String userName, String password) {
        try {
            DriverManager.setLoginTimeout(3);
            return DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // update/delete/insert
    // sql格式:UPDATE tablename SET columnn = ? WHERE column = ?
    public static void executeUpdate(Connection conn, List<String> sqlArray, String[] parameters) {
        PreparedStatement ps = null;
        log.error("开始执行sql");
        try {
            conn.setAutoCommit(false);
            for (String sql : sqlArray) {
                //log.error(sql);
                ps = conn.prepareStatement(sql);
                if (parameters != null) {
                    for (int i = 0; i < parameters.length; i++) {
                        ps.setString(i + 1, parameters[i]);
                    }
                }
                ps.executeUpdate();
                conn.commit();//分批提交，每一条sql会产生大量的插入数据，分批提交更合适，防止巨大的事务产生占用资源过多。
                System.out.print(".");
            }

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();// 开发阶段
            throw new RuntimeException(e.getMessage());
        } finally {
            // 关闭资源
            close(null, ps, conn);
        }
        System.out.println("");
        log.error("执行sql完毕");

    }

    // select
    public static ResultSet executeQuery(Connection conn, String sql, String[] parameters) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            if (parameters != null) {
                for (int i = 0; i < parameters.length; i++) {
                    ps.setString(i + 1, parameters[i]);
                }
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {

        }
        return rs;
    }


    public static void close(ResultSet rs, Statement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        rs = null;
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ps = null;
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        conn = null;
    }

}
