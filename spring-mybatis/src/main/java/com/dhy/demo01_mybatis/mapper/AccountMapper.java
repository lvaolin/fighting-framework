package com.dhy.demo01_mybatis.mapper;

import com.dhy.demo01_mybatis.annotation.MySelect;

public interface AccountMapper {
    @MySelect("select * from account where id = 1")
    Object getAccount();
}
