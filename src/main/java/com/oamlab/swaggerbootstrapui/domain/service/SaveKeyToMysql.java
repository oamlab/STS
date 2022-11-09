package com.oamlab.swaggerbootstrapui.domain.service;

import com.oamlab.swaggerbootstrapui.infrastructure.persistent.po.Key;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class SaveKeyToMysql {

        public Map<Object, Object> saveKeys(String randomStringKeyId, String randomStringKey) throws IOException {

        //1、加载Mybatis核心配置文件，获取SqlSessionFactory
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、获取sqlSession对象，用来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Key key = new Key();
        key.setId(null);
        key.setKeyId(randomStringKeyId);
        key.setCryptKey(randomStringKey);

        sqlSession.insert("test.insert1",key);
        sqlSession.commit();

        //3、利用sqsession执行sql语句
        Map<Object, Object> keys = sqlSession.selectMap("test.selectByKeyId",randomStringKeyId,"keyId");

        //4、释放资源
        sqlSession.close();

        return keys;
    }
}
