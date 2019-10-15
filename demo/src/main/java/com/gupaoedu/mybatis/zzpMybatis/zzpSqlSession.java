package com.gupaoedu.mybatis.zzpMybatis;

import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;

public class zzpSqlSession {

    zzpConfiguration configuration;

    zzpExcutor executor;

    public zzpSqlSession(zzpConfiguration configuration, zzpExcutor excutor) {
        this.configuration = configuration;
        this.executor = excutor;
    }

    public <T> T getMapper(Class<T> type) {
        return configuration.<T>getMapper(type, this);
    }

    public Object selectOne(String statement, String parameter) {
        try {
            return executor.query(statement, parameter);
        } catch (Exception e) {
            throw ExceptionFactory.wrapException("Error querying database.  Cause: " + e, e);
        } finally {
            ErrorContext.instance().reset();
        }
    }
}
