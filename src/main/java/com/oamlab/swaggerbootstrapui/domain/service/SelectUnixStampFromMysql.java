package com.oamlab.swaggerbootstrapui.domain.service;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class SelectUnixStampFromMysql {
    public Map<Object, Object> getUnixStamp() throws IOException {

        //1、加载Mybatis核心配置文件，获取SqlSessionFactory
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、获取sqlSession对象，用来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3、利用sqsession执行sql语句
        String reserveAb = "reserveAb";
        Map<Object, Object> unixStampInMysql = sqlSession.selectMap("test.selectUnixTimeStamp",reserveAb,reserveAb);

        //4、释放资源
        sqlSession.close();

        return unixStampInMysql;
    }
}
