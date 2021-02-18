package com.dhy.demo.spring.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
class JdbcApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() throws Exception {
        //默认是 hikari 连接池，可以换成  druid试试
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
        DruidDataSource druidDataSource = (DruidDataSource)dataSource;
        System.out.println(druidDataSource.getInitialSize());
        System.out.println(druidDataSource.getMaxActive());
    }

}
