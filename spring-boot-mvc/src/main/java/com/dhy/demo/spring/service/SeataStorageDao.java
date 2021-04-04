package com.dhy.demo.spring.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class SeataStorageDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public List<SeataStoragePo> productSelectAll(){
        //封装行数据映射
        RowMapper<SeataStoragePo> rowMapper=new RowMapper<SeataStoragePo>() {
            @Override
            public SeataStoragePo mapRow(ResultSet resultSet, int i) throws SQLException {

                SeataStoragePo seataStoragePo = new SeataStoragePo();
                seataStoragePo.setId(resultSet.getInt("id"));
                seataStoragePo.setPrice(resultSet.getDouble("price"));
                seataStoragePo.setStock(resultSet.getInt("stock"));
                seataStoragePo.setLastUpdateTime(resultSet.getDate("last_update_time"));
                return seataStoragePo;
            }
        };
        return jdbcTemplate.query("select *  from product", rowMapper);
    }

    public int productCount(){
        Map<String, Object> map = jdbcTemplate.queryForMap("select count(*) as count  from product ");
        return Integer.parseInt(map.get("count").toString());
    }


    public int productInsert(SeataStoragePo po){
        return jdbcTemplate.update("insert into product (id,price,stock) values (?,?,?) ",po.getId(),po.getPrice(),po.getStock());
    }

    public int productDelete(SeataStoragePo po){
        return jdbcTemplate.update("delete from product where id = ? ",po.getId());
    }


    public int productUpdate(SeataStoragePo po){
        return jdbcTemplate.update("update  product set stock=? and price = ?  where id = ? ",po.getStock(),po.getPrice(),po.getId());
    }

}
