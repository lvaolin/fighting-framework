package com.dhy.demo02_mybatis;

import com.dhy.demo01_mybatis.dto.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("select * from boss_user where id= #{id}")
    BossUser getUser(@Param("id") Long id);
}
