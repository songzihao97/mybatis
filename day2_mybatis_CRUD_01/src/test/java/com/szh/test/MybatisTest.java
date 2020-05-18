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
     * 测试查询用户
     */
    @Test
    public void testFindAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 测试保存
     */
    @Test
    public void testSave(){
        User user=new User("李四id","山东","男", new Date());
        System.out.println("保存操作前"+"-->"+user);
        //调用方法
        userDao.saveUser(user);
        System.out.println("保存操作后"+"-->"+user);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate(){
        User user=new User("mybatis李","山东","男", new Date());
        user.setId(50);
        //调用方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete(){
        userDao.deleteUser(50);
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
     * 测试查询总用户数
     */
    @Test
    public void testFindTotal(){
        Integer total = userDao.findTotal();
        System.out.println(total);
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
}
