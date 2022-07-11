package com.gupaoedu.mybatis.zzpMybatis;

import com.gupaoedu.mybatis.mapper.TestMapper;

public class zzpDemo {
    public static void main(String[] args) {
        zzpSqlSession sqlSession = new zzpSqlSession(new zzpConfiguration(), new SimpleExecutor());
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        System.out.println(testMapper.selectByPrimaryKey(1));
    }
}
