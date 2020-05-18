package com.szh.test;

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
    private SqlSession session=null;
    private AccountDao accountDao;

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
        accountDao= session.getMapper(AccountDao.class);
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
     * 测试查询所有账户信息
     */
    @Test
    public void TestFindAll(){
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
//            System.out.println("-------------每一个account信息-------------");
//            System.out.println(account);
//            System.out.println(account.getUser());
        }
    }

    /**
     * 查询根据uid查询账户信息
     */
    @Test
    public void testFindByUid(){
        List<Account> accounts = accountDao.findAccountByUid(46);
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

}
