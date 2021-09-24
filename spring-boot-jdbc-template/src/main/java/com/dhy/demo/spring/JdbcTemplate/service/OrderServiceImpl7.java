package com.dhy.demo.spring.JdbcTemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.UUID;

@Service
public class OrderServiceImpl7 implements IOrderService {
    @Autowired
    private DataSource dataSource;
    ThreadLocal<Connection> myThreadLocal = new ThreadLocal<>();
    /**
     * 模拟spring事务传播特性：PROPAGATION_NESTED：
     * 没有就新建一个,有就在当前事务中嵌套其他事务
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
        Savepoint savepoint001 = null;
        if (parentConn.getAutoCommit()) {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            myThreadLocal.set(connection);
        }else{
            savepoint001 = connection.setSavepoint("001");
        }

        try {
            Statement statement = parentConn.createStatement();
            String sql = "insert into orders (`id`, `user_id`, `product_id`, `product_name`) values ('"+ UUID.randomUUID() +"','"+UUID.randomUUID()+"','"+UUID.randomUUID()+"','order101苹果') ";
            System.out.println(sql);
            statement.execute(sql);
            //可能继续调其它方法，传播事务-------
        }catch (Exception e){
            if (savepoint001!=null) {
                connection.rollback(savepoint001);
            }else{
                connection.rollback();
            }
        }


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
