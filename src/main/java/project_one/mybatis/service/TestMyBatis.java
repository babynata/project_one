package project_one.mybatis.service;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class TestMyBatis {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    public void TestBatch() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);

    }
}
