package com.dhy.demo03_mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@ComponentScan("com.dhy.demo03_mybatis")
@Configuration
@MapperScan("com.dhy.demo03_mybatis.mapper")
public class SpringConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        return  sqlSessionFactoryBean.getObject();
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/zhuying_boss");
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return druidDataSource;
    }
//这是单个配置mapper的方式，实际工作中因为mapper很多，所以不会采用这种方式
    //应该采用 扫描mapper包的方式  加类注解：@MapperScan("com.dhy.demo03_mybatis.mapper")
//    @Bean
//    public UserMapper userMapper() throws Exception {
//        MapperFactoryBean<UserMapper> mapperFactoryBean = new MapperFactoryBean();
//        mapperFactoryBean.setMapperInterface(UserMapper.class);
//        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory());
//        return mapperFactoryBean.getObject();
//    }
}
