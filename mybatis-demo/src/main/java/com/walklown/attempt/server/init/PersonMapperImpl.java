package com.walklown.attempt.server.init;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

@Component
public class PersonMapperImpl extends SqlSessionDaoSupport implements PersonMapper {

    public PersonMapperImpl(SqlSessionFactory sqlSessionFactory) {
        super();
        this.setSqlSessionFactory(sqlSessionFactory);
    }

    public void insert(String name) {
        this.getSqlSession().insert("insert", "fred");
    }
}
