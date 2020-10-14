package com.dhy.demo01.mapper;

import com.dhy.demo01.annotation.MySelect;

public interface OrderMapper {
    @MySelect("select * from order where id = 1")
    Object getOrder();
}
