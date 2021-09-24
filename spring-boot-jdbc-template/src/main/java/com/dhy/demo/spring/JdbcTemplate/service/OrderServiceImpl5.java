package com.dhy.demo.spring.JdbcTemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

@Service
public class OrderServiceImpl5 implements IOrderService {
    @Autowired
    private DataSource dataSource;
    ThreadLocal<Connection> myThreadLocal = new ThreadLocal<>();
    /**
     * 模拟spring事务传播特性：PROPAGATION_NOT_SUPPORTED：
     * 没有就以非事务方式执行；有就将当前事务挂起。即此方法不支持事务
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


    private void order101() throws SQLException {
        //1、保存上下文信息
        Connection parentConn =myThreadLocal.get();

        Connection connection = null;
        if (!parentConn.getAutoCommit()) {
            //非自动提交模式则挂起当前连接
            //新建连接使用自动提交模式---------
            connection = dataSource.getConnection();
            connection.setAutoCommit(true);
            myThreadLocal.set(connection);
        }else{
            //自动提交模式，直接复用当前连接
            connection = parentConn;
        }

        Statement statement = connection.createStatement();
        String sql = "insert into orders (`id`, `user_id`, `product_id`, `product_name`) values ('"+ UUID.randomUUID() +"','"+UUID.randomUUID()+"','"+UUID.randomUUID()+"','order101苹果') ";
        System.out.println(sql);
        statement.execute(sql);
        //可能继续调其它方法，传播事务-------

        //3、恢复上下文信息
        myThreadLocal.set(parentConn);
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




}
