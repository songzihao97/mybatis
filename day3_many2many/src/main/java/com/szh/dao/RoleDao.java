package com.szh.dao;

import com.szh.domain.Role;

import java.util.List;

public interface RoleDao {

    /**
     * 查询所有
     * @return
     */
    List<Role> findAll();
}
