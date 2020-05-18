package com.szh.test;


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
import java.util.List;

/**
 * 测试mybatis的CRUD操作
 */
public class UserTest {


    private InputStream is=null;
    private SqlSession session=null;
    private UserDao userDao;
    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception{
        //读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获取工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        factory = builder.build(is);
        //获取SqlSession对象
        session = factory.openSession(true);
        //获取dao代理对象
        userDao= session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {

        //释放资源
        session.close();
        is.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("---------每一个用户-----------");
            System.out.println(user);
        }
    }

    /**
     * 测试根据id查找用户
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(48);
        System.out.println(user);
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFindFirstLevelCache(){
        User user1 = userDao.findById(41);
        System.out.println(user1);

        /*session.close();
        SqlSession session = factory.openSession(true);
        UserDao userDao = session.getMapper(UserDao.class);*/

        session.clearCache();//此方法也可以清除缓存

        User user2 =userDao.findById(41);
        System.out.println(user2);

        System.out.println(user1==user2);
    }

    /**
     * 测试缓存同步
     */
    @Test
    public void testUpdateUser(){
        //查询用户信息
        User user1 = userDao.findById(52);
        System.out.println(user1);

        //修改用户信息
        user1.setUsername("李四");
        user1.setAddress("山东");
        userDao.updateUser(user1);

        User user2 = userDao.findById(52);
        System.out.println(user2);
        System.out.println(user1==user2);
    }
}
