package com.szh.tset;

import com.szh.dao.AccountDao;
import com.szh.domain.Account;
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

public class AccountTest {

    private InputStream is=null;
    private SqlSession sqlSession=null;
    private AccountDao accountDao;

    @Before
    public void init() throws IOException {
        //创建流导入文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        //创建SqlSession
        sqlSession = factory.openSession(true);
        //创建UserDAo代理对象
        accountDao= sqlSession.getMapper(AccountDao.class);
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
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println("----------------------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 根据用户id查询
     */
    @Test
    public void testFindByUid(){
        List<Account> accounts = accountDao.findByUid(46);
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
