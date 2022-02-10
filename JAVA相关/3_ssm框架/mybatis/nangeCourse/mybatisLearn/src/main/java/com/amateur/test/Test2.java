package com.amateur.test;

import com.amateur.entity.Account;
import com.amateur.repository.AccountRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        //加载mybatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");

        //sqlsessionfactorybuilder->sqlsessionfactory(传入mybatis配置文件建立)->sqlsession
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取接口代理对象
        AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);

        //查找全部
//        List<Account> list = accountRepository.findAll();
//        for(Account account:list){
//            System.out.println(account);
//        }

        //添加对象
//        Account account = new Account(2L,"王五","123123",12);
//        accountRepository.save(account);
//        sqlSession.commit();

        //查找单个（id）
//        Account account = accountRepository.findById(3L);
//        System.out.println(account);

        //修改
//        Account account = accountRepository.findById(3L);
//        account.setUsername("小明");
//        account.setPassword("2");
//        account.setAge(123);
//        int result = accountRepository.update(account);//改完后记得保存
//        sqlSession.commit();
//        System.out.println(result);
       // System.out.println(accountRepository.findById(3L));

        //删除
//        int result = accountRepository.deleteById(3L);
//        System.out.println(result);
//        sqlSession.commit();

        //根据用户名查找
        //System.out.println(accountRepository.findByName("张三"));

        //传入包装类Long，解决基本类型不能接null
//        System.out.println(accountRepository.findById2(Long.parseLong("1")));

        //传两个参数
//        System.out.println(accountRepository.findByNameAndAge("张三",21));

        //统计账号总个数
//        System.out.println(accountRepository.count());
        System.out.println(accountRepository.count2());

        sqlSession.close();
    }
}
