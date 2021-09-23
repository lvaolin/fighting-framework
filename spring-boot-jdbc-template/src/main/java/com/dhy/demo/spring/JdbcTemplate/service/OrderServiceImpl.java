package com.dhy.demo.spring.JdbcTemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private DataSource dataSource;
    ThreadLocal<Connection> myThreadLocal = new ThreadLocal<>();
    /**
     * 模拟spring事务传播特性：PROPAGATION_REQUIRED：默认事务类型，有就复用，没有就新建。
     * @return
     */
    @Override
    public boolean order1(){
        try {
            order101();
            order102();
            order103();
            int x = 1/0;
            //提交
            myThreadLocal.get().commit();
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
    //PROPAGATION_REQUIRED
    private void order101() throws SQLException {
        Connection connection =myThreadLocal.get();
        if (connection==null) {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            myThreadLocal.set(connection);
        }
        Statement statement = connection.createStatement();
        String sql = "insert into orders (`id`, `user_id`, `product_id`, `product_name`) values ('"+ UUID.randomUUID() +"','"+UUID.randomUUID()+"','"+UUID.randomUUID()+"','order101苹果') ";
        System.out.println(sql);
        statement.execute(sql);

    }
    //PROPAGATION_REQUIRED
    private void order102() throws SQLException{
        Connection connection =myThreadLocal.get();
        if (connection==null) {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            myThreadLocal.set(connection);
        }
        Statement statement = connection.createStatement();
        String sql = "insert into orders (`id`, `user_id`, `product_id`, `product_name`) values ('"+ UUID.randomUUID() +"','"+UUID.randomUUID()+"','"+UUID.randomUUID()+"','order102苹果') ";
        System.out.println(sql);
        statement.execute(sql);
    }
    //PROPAGATION_REQUIRED
    private void order103() throws SQLException{
        Connection connection =myThreadLocal.get();
        if (connection==null) {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            myThreadLocal.set(connection);
        }
        Statement statement = connection.createStatement();
        String sql = "insert into orders (`id`, `user_id`, `product_id`, `product_name`) values ('"+ UUID.randomUUID() +"','"+UUID.randomUUID()+"','"+UUID.randomUUID()+"','order103苹果') ";
        System.out.println(sql);
        statement.execute(sql);
    }



}
