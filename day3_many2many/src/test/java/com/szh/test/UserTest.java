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

    @Before
    public void init() throws Exception{
        //读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //获取工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //获取SqlSession对象
        session = factory.openSession();
        //获取dao代理对象
        userDao= session.getMapper(UserDao.class);
    }

    @After
    public void destroy() throws IOException {
        //提交事务
        session.commit();
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
     * 测试查询所有
     */
    @Test
    public void testFindAllRole(){
        List<User> roles = userDao.findAllRole();
        for (User role : roles) {
            System.out.println("---------每一个用户-----------");
            System.out.println(role);
            System.out.println(role.getRoles());
        }
    }
}
