package com.dhy.demo01.mapper;

import com.dhy.demo01.annotation.MySelect;

public interface AccountMapper {
    @MySelect("select * from account where id = 1")
    Object getAccount();
}
