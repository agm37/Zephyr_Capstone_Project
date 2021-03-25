package capstone.zephyr.zephyr.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAccess {
    @Autowired
    private JdbcTemplate databaseTemplate;
    private String queryResult;

    public void queryDatabase() {
        String sqlString = "SELECT user_name, user_password FROM company_logins WHERE user_name = 'admin';";

        SqlRowSet queryRow = databaseTemplate.queryForRowSet(sqlString);
        queryResult = queryRow.getNString(1);

        System.out.println(queryResult);
    }

    public String getQueryResult() {
        return queryResult;
    }
}
