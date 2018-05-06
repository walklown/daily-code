package com.zzp.pattern.template;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class JdbcTemplate {

    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<?> executeQuery(String sql, Object[] values){
        try{
            Connection conn = dataSource.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            List<?> result = processResult(rs);
            rs.close();
            statement.close();
            conn.close();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public abstract List<?> processResult(ResultSet result) throws SQLException;

}
