package com.dhy.mysql;



import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;

/**
 * mysql预编译问题测试
 *
 * @author lvaolin
 * @create 2019/10/8 3:45 PM
 *
 * 1、mysql server4.1之前不支持预编译
 * 2、mysql是否默认开启预编译，与MySQL server的版本无关，而与 MySQL Connector/J（驱动程序）的版本有关，Connector/J 5.0.5及以后的版本默认不支持预编译，Connector/J 5.0.5之前的版本默认支持预编译
 * 3、对于Connector/J5.0.5以上的版本，若使用useServerPrepStmts=true开启预编译，则一定要同时使用cachePrepStmts=true 开启预编译缓存，否则性能会下降，若二者都开启，性能并没有显著的提高
 * 4、对于mysql，开启了预编译缓存后，不同connection之间，预编译的结果是独立的，是无法共享的，一个connection无法得到另外一个connection的预编译缓存结果，也就是预编译缓存是和conn关联的，conn关闭后预编译缓存就会丢失
 */

public class PrepareTest {
    public void  setup(){
         System.out.println("before...");
    }
    @Test
    public void test() throws Exception{
        System.out.println("test....");
        String sql = "select * from rbac_user where user_name =?";
        String sql2 = "select * from rbac_user where user_password =?";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = null;
        Connection conn2 = null;
        //&useServerPrepStmts=true
        String url = "jdbc:mysql://127.0.0.1:3306/dahuangya_rbac?user=root&password=root&useUnicode=true&characterEncoding=UTF8&allowMultiQueries=true&useServerPrepStmts=false&cachePrepStmts=false";
        try{
            conn = DriverManager.getConnection(url);
            conn2 = DriverManager.getConnection(url);

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "dahuangya1");
            ResultSet rs = stmt.executeQuery();
            rs.close();
            stmt.close();

            stmt = conn.prepareStatement(sql2);
            stmt.setString(1, "dahuangya2");
            rs = stmt.executeQuery();
            rs.close();
            stmt.close();


            PreparedStatement stmt2 = conn2.prepareStatement(sql);
            stmt2.setString(1, "dahuangya3");
            rs = stmt2.executeQuery();
            rs.close();
            stmt2.close();

            stmt2 = conn2.prepareStatement(sql);
            stmt2.setString(1, "dahuangya4");
            rs = stmt2.executeQuery();
            rs.close();
            stmt2.close();

            while (true){
                TimeUnit.SECONDS.sleep(1);
            }


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            conn.close();
            conn2.close();
        }
    }
    public void after(){
        System.out.println("after.....");
    }

}
