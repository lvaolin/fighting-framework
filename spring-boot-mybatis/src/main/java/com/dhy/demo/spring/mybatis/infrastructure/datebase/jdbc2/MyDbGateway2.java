package com.dhy.demo.spring.mybatis.infrastructure.datebase.jdbc2;

import com.dhy.demo.spring.mybatis.infrastructure.datebase.jdbc.DBUtil;
import com.dhy.demo.spring.mybatis.po.SeataStoragePo;
import com.dhy.demo.spring.mybatis.service.gateway.IDbGateway;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project spring-boot-mybatis
 * @Description 主要用途描述
 * @Author lvaolin
 * @Date 2022/11/29 下午5:51
 */
@Component
public class MyDbGateway2 implements IDbGateway {
    @Override
    public List<SeataStoragePo> productSelectAll() {
        List<SeataStoragePo> list = new ArrayList<>();
        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery("select * from emp");
            while (rs.next()) {
                int id = rs.getInt(1);
                System.out.println(id);
                SeataStoragePo po = new SeataStoragePo();
                po.setId(id);
                list.add(po);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            try {
                rs.close();
                st.close();
                //conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        return list;
    }

    @Override
    public int productCount() {
        return 0;
    }

    @Override
    public int productInsert(SeataStoragePo po) {
        return 0;
    }

    @Override
    public int productDelete(SeataStoragePo po) {
        return 0;
    }

    @Override
    public int productUpdate(SeataStoragePo po) {
        return 0;
    }

    @Override
    public List<SeataStoragePo> productSelectAllByXml() {
        return null;
    }
}
