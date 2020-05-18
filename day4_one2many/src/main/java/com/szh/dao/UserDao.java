package com.szh.dao;

import com.szh.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 在mybatis中针对CRUD共有四个注解
 * @select @insert @update @delete
 */
@CacheNamespace(blocking = true)//开启二级缓存
public interface UserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("Select * from user")
    @Results(id = "userMap",value = {
            /*column="数据库字段名" property="实体类属性"*/
            @Result(id=true,property = "id",column ="id"),
            @Result(property = "username",column ="username"),
            @Result(property = "sex",column ="sex"),
            @Result(property = "address",column ="address"),
            @Result(property = "birthday",column ="birthday"),
            @Result(property = "accounts",column = "id",many = @Many(select = "com.szh.dao.AccountDao.findByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from user where id=#{id}")
    User findById(Integer id);

    /**
     * 根据名称模糊查询
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}")
    List<User> findUserByName(String username);

}
