package com.szh.dao;

import com.szh.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 在mybatis中针对CRUD共有四个注解
 * @select @insert @update @delete
 */
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("Select * from user")
    List<User> findAll();

    /**
     * 增加用户
     * @param user
     */
    @Insert("insert user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 修改用户信息
     * @param user
     */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void upDateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User FindById(Integer id);

    /**
     * 根据名称模糊查询
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}")
    List<User> findUserByName(String username);

    /**
     * 查询用户数
     * @return
     */
    @Select("select count(id) from user")
    int findTotal();
}
