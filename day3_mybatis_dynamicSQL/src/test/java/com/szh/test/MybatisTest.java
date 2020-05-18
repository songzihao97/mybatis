package com.szh.test;


import com.szh.dao.UserDao;
import com.szh.domain.QueryVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 测试mybatis的CRUD操作
 */
public class MybatisTest {


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
        session = factory.openSession(true);
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
     * 根据name模糊查询
     */
    @Test
    public void testFindByName(){
        List<User> users = userDao.findByName("%王%");
        /*List<User> users = userDao.findByName("王");*/
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试根据QueryVo作为查询条件
     */
    @Test
    public void testFindByVo(){
        QueryVo vo=new QueryVo();
        User user1=new User();
        user1.setUsername("%王%");
        vo.setUser(user1);
        List<User> users = userDao.findByVo(vo);
        /*List<User> users = userDao.findByName("王");*/
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试条件查询
     */
    @Test
    public void testFindByCondition(){
        User user=new User();
        user.setUsername("老王");
        user.setSex("男");
        List<User> users = userDao.findUserByCondition(user);
        for (User u : users) {
            System.out.println(u);
        }
    }

    /**
     * 测试条件查询
     */
    @Test
    public void testFindByQueryIds(){
        QueryVo vo=new QueryVo();
        List<Integer> list=new ArrayList<>();
        list.add(41);
        list.add(42);
        list.add(43);
        vo.setIds(list);
        List<User> user = userDao.findUserInIds(vo);
        for (User u : user) {
            System.out.println(u);
        }
    }
}
