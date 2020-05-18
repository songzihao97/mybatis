package com.szh.dao;

import com.szh.domain.Account;

import java.util.List;

public interface AccountDao {

    /**
     * 查询所有账户
     * @return
     */
    List<Account> findAll();

    /**
     * 根据uid查询account
     * @param Uid
     * @return
     */
    List<Account> findAccountByUid(Integer Uid);

}
