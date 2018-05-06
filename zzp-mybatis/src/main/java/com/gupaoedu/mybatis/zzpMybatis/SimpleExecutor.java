package com.gupaoedu.mybatis.zzpMybatis;

import com.gupaoedu.mybatis.beans.Test;

import java.sql.*;

public class SimpleExecutor implements zzpExcutor {
    @Override
    public <T>T query(String statement, String parameter) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Test test = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/zzp?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            preparedStatement = connection.prepareStatement(String.format(statement, Integer.parseInt(parameter)));
//            preparedStatement.setInt(1, Integer.parseInt(parameter));
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                test = new Test();
                test.setId(rs.getInt(1));
                test.setNums(rs.getInt(2));
                test.setName(rs.getString(3));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T)test;
    }
}
