package com.dhy.mlife.billingservice.gateway.db.impl.mapper;

import com.dhy.mlife.billingservice.gateway.db.itf.SeataStoragePo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

import java.util.List;

/**
 * 使用了tkmybatis 继承 Mapper
 */

public interface SeataStorageMapper extends Mapper<SeataStoragePo>, InsertListMapper<SeataStoragePo>, MySqlMapper<SeataStoragePo>, IdsMapper<SeataStoragePo>, ConditionMapper<SeataStoragePo> {


    @Select("select count(*) from product")
    public int productCount();

    @Insert("insert into product(price,stock) values ( #{price},#{stock} )")
    public int productInsert(SeataStoragePo po);

    @Delete("delete from product where id= #{id}")
    public int productDelete(SeataStoragePo po);

    @Delete("update  product set price=#{price},stock=#{stock} where id= #{id}")
    public int productUpdate(SeataStoragePo po);

    //使用xml映射sql举例，可以实现很复杂的动态sql，这是注解做不到的
    public List<SeataStoragePo> productSelectAllByXml();

}
