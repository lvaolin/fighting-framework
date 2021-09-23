package com.dhy.demo.spring.JdbcTemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Service
public class OrderServiceImpl2 implements IOrderService {
    @Autowired
    private DataSource dataSource;
    ThreadLocal<Connection> myThreadLocal = new ThreadLocal<>();
    /**
     * 模拟spring事务传播特性：PROPAGATION_SUPPORTS：没有就以非事务方式执行；有就使用当前事务。
     * @return
     */
    @Override
    public boolean order1(){
        try {
            beginTransaction();
            order101();
            order102();
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

    //PROPAGATION_SUPPORTS
    private void order101() throws SQLException {
        Connection connection =myThreadLocal.get();
        if (connection==null) {
            connection = dataSource.getConnection();
            //没有就以非事务方式执行
            connection.setAutoCommit(true);
            myThreadLocal.set(connection);
        }
        Statement statement = connection.createStatement();
        String sql = "insert into orders (`id`, `user_id`, `product_id`, `product_name`) values ('"+ UUID.randomUUID() +"','"+UUID.randomUUID()+"','"+UUID.randomUUID()+"','order101苹果') ";
        System.out.println(sql);
        statement.execute(sql);

    }
    //PROPAGATION_SUPPORTS
    private void order102() throws SQLException{
        Connection connection =myThreadLocal.get();
        if (connection==null) {
            connection = dataSource.getConnection();
            connection.setAutoCommit(true);
            myThreadLocal.set(connection);
        }
        Statement statement = connection.createStatement();
        String sql = "insert into orders (`id`, `user_id`, `product_id`, `product_name`) values ('"+ UUID.randomUUID() +"','"+UUID.randomUUID()+"','"+UUID.randomUUID()+"','order102苹果') ";
        System.out.println(sql);
        statement.execute(sql);
    }




}
