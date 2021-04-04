package com.dhy.demo02_mybatis;

import com.dhy.demo01_mybatis.dto.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {

        String xml = "mybatis-config.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(xml);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        for (int i = 0; i <100 ; i++) {
            SqlSession sqlSession = sessionFactory.openSession();
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            BossUser user = mapper.getUser(1L);
            System.out.println(user.toString());
            sqlSession.commit();
            sqlSession.clearCache();
            sqlSession.close();
        }

        synchronized (Main.class){
            try {
                Main.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
