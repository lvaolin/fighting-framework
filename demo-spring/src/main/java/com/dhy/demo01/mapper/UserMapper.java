package com.dhy.demo01.mapper;

import com.dhy.demo01.annotation.MySelect;
import com.dhy.demo01.dto.User;

public interface UserMapper {
    @MySelect("select * from user where id = 1")
    User getUser();
}
