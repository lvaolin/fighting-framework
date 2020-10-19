package com.dhy.demo01_elasticjob.mapper;

import com.dhy.demo01_elasticjob.annotation.MySelect;
import com.dhy.demo01_elasticjob.dto.User;

public interface UserMapper {
    @MySelect("select * from user where id = 1")
    User getUser();
}
