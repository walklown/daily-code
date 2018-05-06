package com.gupaoedu.mybatis.zzpMybatis;

public interface zzpExcutor {
    public <T>T query(String statement, String parameter);
}
