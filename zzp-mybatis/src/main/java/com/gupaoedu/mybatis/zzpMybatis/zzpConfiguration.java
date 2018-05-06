package com.gupaoedu.mybatis.zzpMybatis;

import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class zzpConfiguration {

    public <T>T getMapper(Class<T> T, zzpSqlSession sqlSession){
        return (T)Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{T},
                new zzpMapperproxy(sqlSession));
    }

    static class TestMapperXml{
        public static final String namespace = "com.gupaoedu.mybatis.mapper.TestMapper";
        public static final Map<String, String> methodSqlMap = new HashMap<>();

        static {
            methodSqlMap.put("selectByPrimaryKey", "select * from Test where id=%d");
        }
    }
}
