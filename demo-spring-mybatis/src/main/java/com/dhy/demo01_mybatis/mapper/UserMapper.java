package com.dhy.demo01_mybatis.mapper;

import com.dhy.demo01_mybatis.annotation.MySelect;
import com.dhy.demo01_mybatis.dto.User;

public interface UserMapper {
    @MySelect("select * from user where id = 1")
    User getUser();
}
