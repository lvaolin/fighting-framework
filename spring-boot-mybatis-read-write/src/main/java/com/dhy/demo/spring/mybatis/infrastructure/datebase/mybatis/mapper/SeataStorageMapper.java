package com.dhy.demo.spring.mybatis.infrastructure.datebase.mybatis.mapper;

import com.dhy.demo.spring.mybatis.po.SeataStoragePo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface SeataStorageMapper  {

    //简单sql用注解更方便
    //@Select("select * from product")
    @Select("select * from product")
    public List<SeataStoragePo> productSelectAll();
    @Select("select count(*) from product")
    public int productCount();
    @Insert("insert into product(id,price,stock) values ( #{id},#{price},#{stock} )")
    public int productInsert(SeataStoragePo po);
    @Delete("delete from product where id= #{id}")
    public int productDelete(SeataStoragePo po);
    @Delete("update  product set price=#{price},stock=#{stock} where id= #{id}")
    public int productUpdate(SeataStoragePo po);

    //使用xml映射sql举例，可以实现很复杂的动态sql，这是注解做不到的
    public List<SeataStoragePo> productSelectAllByXml();

}
