package databasepool;/**
 * Created by lvaolin on 17/9/29.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 模式数据库连接池实现
 *
 * @author lvaolin
 * @create 17/9/29 上午10:02
 */
public class ConnectionPool {

    private static LinkedList<Connection> pool = new LinkedList<Connection>();


    public ConnectionPool(int i){

        if(i>0){
            try {
                Class.forName("org.postgresql.Driver");
                for (int j = 0; j <i ; j++) {
                    pool.addLast(DriverManager.getConnection("jdbc:postgresql://10.11.70.119:5432/b2bshop-dev", "b2bshop", "b2bshop"));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * mills 是返回超时时间，如果时间超时则返回null
     * @param mills
     * @return
     */
    public   Connection getConnection(long mills) throws InterruptedException {



        synchronized (pool){

            if(mills<=0){
                  //不超时  等待 直到有可用连接为止
                while (true){
                    if(pool.isEmpty()){
                        pool.wait();
                    }
                }
            }else{
                long stoptime = System.currentTimeMillis()+mills;
                long remaining = mills;
                while (pool.isEmpty()&&remaining>0){
                    pool.wait(remaining);
                    remaining = stoptime - System.currentTimeMillis();
                }

                if (!pool.isEmpty()){
                    return pool.removeFirst();
                }else{
                    return null;
                }

            }


        }



    }

    /**
     * 释放connection到连接池
     * @param connection
     */
    public   void releaseConnection(Connection connection){

            if(connection!=null){
                synchronized (pool){
                    pool.addLast(connection);
                    pool.notifyAll();
                }
            }
    }



}
