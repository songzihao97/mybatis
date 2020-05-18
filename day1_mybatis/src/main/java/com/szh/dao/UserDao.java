package com.szh.dao;

import com.szh.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserDao {
    /**
     * 查询所有
     */
    List<User> findAll();
}
