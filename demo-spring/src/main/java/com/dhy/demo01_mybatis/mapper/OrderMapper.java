package com.dhy.demo01_mybatis.mapper;

import com.dhy.demo01_mybatis.annotation.MySelect;

public interface OrderMapper {
    @MySelect("select * from order where id = 1")
    Object getOrder();
}
