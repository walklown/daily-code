package com.gupaoedu.mybatis.zzpMybatis;

import com.gupaoedu.mybatis.beans.Test;
import com.gupaoedu.mybatis.mapper.TestMapper;
import org.apache.ibatis.session.SqlSession;

import java.io.FileNotFoundException;

public class zzpDemo {
    public static void main(String[] args) throws FileNotFoundException {
        SqlSession sqlSession = null;
        try {
            TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
            System.out.println(testMapper.selectByPrimaryKey(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
