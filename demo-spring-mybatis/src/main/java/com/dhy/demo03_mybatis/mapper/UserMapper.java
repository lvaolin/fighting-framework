package com.dhy.demo03_mybatis.mapper;

import com.dhy.demo03_mybatis.BossUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from boss_user where id= #{id}")
    BossUser getUser(@Param("id") Long id);
}
