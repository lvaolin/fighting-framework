package com.dhy.demo01_mybatis.service;

import com.dhy.demo01_mybatis.itf.IAccountService;
import com.dhy.demo01_mybatis.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public Object getAccount(Long id) {
        return accountMapper.getAccount();
    }
}
