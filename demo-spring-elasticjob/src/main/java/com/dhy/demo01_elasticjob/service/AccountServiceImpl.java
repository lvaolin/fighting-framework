package com.dhy.demo01_elasticjob.service;

import com.dhy.demo01_elasticjob.itf.IAccountService;
import com.dhy.demo01_elasticjob.mapper.AccountMapper;
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
