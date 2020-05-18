package com.szh.tset;

import com.szh.dao.UserDao;
import com.szh.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * User的CRUD测试
 */
public class UserTest {

    private InputStream is=null;
    private SqlSession sqlSession=null;
    private UserDao userDao;

    @Before
    public void init() throws IOException {
        //创建流导入文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //创建SqlSession
        sqlSession = factory.openSession(true);
        //创建UserDAo代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.close();
        is.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试插入用户
     */
    @Test
    public void testSaveUser(){
        User user=new User("赵四","山东","男",new Date());
        userDao.saveUser(user);
    }

    /**
     * 测试更改用户
     */
    @Test
    public void testUpdateUser(){
        User user=new User("赵四","山东","女",new Date());
        user.setId(53);
        userDao.upDateUser(user);
    }

    /**
     * 测试删除用户
     */
    @Test
    public void testDeleteUser(){
       userDao.deleteUser(13);
    }

    /**
     * 测试根据id查询
     */
    @Test
    public void testFindById(){
        User user = userDao.FindById(53);
        System.out.println(user);
    }

    /**
     * 测试根据用户名模糊查询
     */
    @Test
    public void testFindUserByName(){
        List<User> users = userDao.findUserByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 查询总数
     */
    @Test
    public void testFindTotal(){
        int count = userDao.findTotal();
        System.out.println(count);
    }

}
