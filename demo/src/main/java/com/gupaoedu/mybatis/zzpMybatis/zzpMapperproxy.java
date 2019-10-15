package com.gupaoedu.mybatis.zzpMybatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class zzpMapperproxy implements InvocationHandler {
    zzpSqlSession sqlSession;

    public zzpMapperproxy(zzpSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass().getName().equals(zzpConfiguration.TestMapperXml.namespace)) {
            String sql = zzpConfiguration.TestMapperXml.methodSqlMap.get(method.getName());
            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }
        return method.invoke(this, args);
    }
}
