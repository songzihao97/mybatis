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
import java.util.List;

public class SecondLevelCatchTest {
    private InputStream is=null;
    private SqlSession sqlSession=null;
    private UserDao userDao;
    private SqlSessionFactory factory;

    @Before
    public void init() throws IOException {
        //创建流导入文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(is);
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
     * 测试根据id查询
     */
    @Test
    public void testFindById(){
        User user = userDao.findById(53);
        System.out.println(user);
        sqlSession.close();//释放一级缓存

        SqlSession sqlSession1 = factory.openSession();
        UserDao userDao1 = sqlSession1.getMapper(UserDao.class);
        User user1 = userDao1.findById(53);
        System.out.println(user1);

    }

}
