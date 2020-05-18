package com.szh.dao;

import com.szh.domain.Account;
import com.szh.domain.AccountUser;

import java.util.List;

public interface AccountDao {

    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户，并且带有用户名称和地址
     * @return
     */
    List<AccountUser> findAllAccount();
}
