package com.dhy.demo.spring.JdbcTemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Service
public class OrderServiceImpl3 implements IOrderService {
    @Autowired
    private DataSource dataSource;
    ThreadLocal<Connection> myThreadLocal = new ThreadLocal<>();
    /**
     * 模拟spring事务传播特性：PROPAGATION_MANDATORY：没有就抛出异常；有就使用当前事务。
     * @return
     */
    @Override
    public boolean order1(){
        try {
            //beginTransaction();
            order101();
            int x = 1/0;
            //提交
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            //回滚
            try {
                if (myThreadLocal.get()!=null) {
                    myThreadLocal.get().rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }finally {
            //关闭连接
            if (myThreadLocal.get()!=null) {
                try {
                    myThreadLocal.get().close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        return true;
    }

    //提交事务
    private void commitTransaction() {
        if (myThreadLocal.get()!=null) {
            try {
                myThreadLocal.get().commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    //开启事务
    private void beginTransaction() {
        try {
            Connection connection =dataSource.getConnection();
            connection.setAutoCommit(false);
            myThreadLocal.set(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void order101() throws SQLException {
        Connection connection =myThreadLocal.get();
        if (connection==null||connection.getAutoCommit()) {
            throw new SQLException("请在事务中运行");
        }
        Statement statement = connection.createStatement();
        String sql = "insert into orders (`id`, `user_id`, `product_id`, `product_name`) values ('"+ UUID.randomUUID() +"','"+UUID.randomUUID()+"','"+UUID.randomUUID()+"','order101苹果') ";
        System.out.println(sql);
        statement.execute(sql);

    }





}
