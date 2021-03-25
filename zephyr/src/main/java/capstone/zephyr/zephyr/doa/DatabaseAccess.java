package capstone.zephyr.zephyr.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAccess {
    @Autowired
    private JdbcTemplate databaseTemplate;
    private String queryResult;

    public void queryDatabase() {
        String sqlString = "SELECT user_name FROM company_logins WHERE company_id = 1;";

        queryResult = databaseTemplate.queryForObject(sqlString, String.class);
    }

    public String getQueryResult() {
        return queryResult;
    }
}
