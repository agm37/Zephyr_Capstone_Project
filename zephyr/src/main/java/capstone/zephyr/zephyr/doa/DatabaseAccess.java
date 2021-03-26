package capstone.zephyr.zephyr.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAccess {
    @Autowired
    private JdbcTemplate databaseTemplate;
    private String queryResult;

    public String queryUserName(String content) {

        String sqlString = "SELECT user_name FROM company_logins WHERE user_name = ?;";
        queryResult = databaseTemplate.queryForObject(sqlString, String.class, content);
        
        return queryResult;
    }

    public String queryPassword(String content) {

        String sqlString = "SELECT user_password FROM company_logins WHERE user_password = ?;";
        queryResult = databaseTemplate.queryForObject(sqlString, String.class, content);
        
        return queryResult;
    }
}
