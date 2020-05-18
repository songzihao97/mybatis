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
            System.out.println("-----------------------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    /**
     * 测试根据id查询
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(53);
        System.out.println(user);
    }

    /**
     * 测试根据名称模糊查询
     */
    @Test
    public void testFindUserByName(){
        List<User> users = userDao.findUserByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }



}
