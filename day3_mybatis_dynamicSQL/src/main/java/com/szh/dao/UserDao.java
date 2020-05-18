package com.szh.dao;

import com.szh.domain.QueryVo;
import com.szh.domain.User;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserDao {
    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据name模糊查询
     * @return
     */
    List<User> findByName(String username);

    /**
     * 根据QueryVo中的条件查询
     * @param vo
     * @return
     */
    List<User> findByVo(QueryVo vo);

    /**
     * 根据传入参数条件
     * @param user 可能存在单一、全部或者空条件
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据QueryVo中的ids查询
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);
}
