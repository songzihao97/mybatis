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

public class SecondLevelCacheTest {

    private InputStream is=null;
    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception{
        //读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(is);
    }

    @After
    public void destroy() throws IOException {
        is.close();
    }

    /**
     * 测试一级缓存
     */
    @Test
    public void testFindFirstLevelCache(){
        SqlSession session = factory.openSession();
        UserDao userDao = session.getMapper(UserDao.class);
        User user1 = userDao.findById(41);
        System.out.println(user1);
        session.close();

        SqlSession session1 = factory.openSession();
        UserDao userDao1 = session1.getMapper(UserDao.class);
        User user2 = userDao1.findById(41);
        System.out.println(user2);
        session1.close();

        System.out.println(user1==user2);
    }
}
