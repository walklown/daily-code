package com.walklown.learn.jarkata.pattern.template;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao extends JdbcTemplate {
    public MemberDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> query() {
        String sql = "select * from t_member";
        return super.executeQuery(sql, null);
    }

    @Override
    public List<?> processResult(ResultSet result) throws SQLException {
        List<Object> items = new ArrayList<>();
        int rowNum = 1;
        while (result.next()) {
            Object obj = result.getArray(rowNum++);
            Member o = (Member) obj;
            items.add(o);
        }
        return null;
    }
}
