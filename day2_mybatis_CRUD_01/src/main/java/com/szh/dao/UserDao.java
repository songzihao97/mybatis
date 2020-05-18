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
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除
     * @param userId
     */
    void deleteUser(Integer userId);

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
     * 查询总用户数
     * @return
     */
    Integer findTotal();

    /**
     * 根据QueryVo中的条件查询
     * @param vo
     * @return
     */
    List<User> findByVo(QueryVo vo);
}
