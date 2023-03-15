package com.walklown.attempt.learn.init;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class PersonRepository {

    public void insert1(SqlSessionFactory sqlSessionFactory) {
        List<String> names = new ArrayList<>();
        names.add("fred");
        names.add("barney");
        names.add("betty");
        names.add("wilma");
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            PersonMapper personMapper = session.getMapper(PersonMapper.class);
            for (String name : names) {
                personMapper.insert(name);
            }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.rollback();
            session.close();
        }
    }
}
