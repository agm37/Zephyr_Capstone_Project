package capstone.zephyr.zephyr.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAccess {
    @Autowired
    private JdbcTemplate databaseTemplate;
    private String finalResult;

    public DatabaseAccess() {
        String sqlString = "SELECT user_name, user_password FROM company_logins WHERE user_name = 'admin';";

        SqlRowSet queryResult = databaseTemplate.queryForRowSet(sqlString);
        finalResult = queryResult.getNString(1);
        System.out.println(finalResult);
    }

    public String getQueryResult() {
        return finalResult;
    }
}
