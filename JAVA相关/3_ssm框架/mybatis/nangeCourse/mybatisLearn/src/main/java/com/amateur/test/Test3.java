package com.amateur.test;

import com.amateur.repository.AccountRepository;
import com.amateur.repository.ClassesRepository;
import com.amateur.repository.StudentRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

//一对多
public class Test3 {
    public static void main(String[] args) {
        //加载mybatis配置文件
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");

        //sqlsessionfactorybuilder->sqlsessionfactory(传入mybatis配置文件建立)->sqlsession
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //获取接口代理对象
        StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
        ClassesRepository classesRepository = sqlSession.getMapper(ClassesRepository.class);

        System.out.println(classesRepository.findById(2L));
    }

}
