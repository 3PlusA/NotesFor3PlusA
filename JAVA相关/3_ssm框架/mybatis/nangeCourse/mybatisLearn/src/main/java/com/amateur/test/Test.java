package com.amateur.test;

import com.amateur.entity.Account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        //加载mybatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");

        //sqlsessionfactorybuilder->sqlsessionfactory(传入mybatis配置文件建立)->sqlsession
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //运用对应方法进行操作
//        String statement = "com.amateur.mapper.AccountMapper.save";//传入对应的方法
//        Account account = new Account(1,"张三","123123",22);
//        sqlSession.insert(statement,account);
//        sqlSession.commit();//提交



        sqlSession.close();
    }
}
