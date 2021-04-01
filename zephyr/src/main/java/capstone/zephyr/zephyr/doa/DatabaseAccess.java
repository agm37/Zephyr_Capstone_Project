package capstone.zephyr.zephyr.doa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseAccess {
    @Autowired
    private JdbcTemplate databaseTemplate;

    private <T> T queryForObjectOrNull(String sql, Class<T> requiredType, Object... args)
            throws DataAccessException {
        try {
            return databaseTemplate.queryForObject(sql, requiredType, args);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    public String queryUserName(String content) {
        String sqlString = "SELECT user_name FROM company_logins WHERE user_name = ?;";
        return queryForObjectOrNull(sqlString, String.class, content);
    }

    public String queryPasswordForUserName(String content) {
        String sqlString = "SELECT user_password FROM company_logins WHERE user_name = ?;";
        return queryForObjectOrNull(sqlString, String.class, content);
    }

    public String queryUserShares(String content) {
        String sqlString = "SELECT num_shares FROM shareholder_info WHERE shareholder_name = ?;";
        return queryForObjectOrNull(sqlString, String.class, content);
    }

    public String queryShareholderName(String content) {
        String sqlString = "SELECT shareholder_name FROM shareholder_info WHERE shareholder_name = ?;";
        return queryForObjectOrNull(sqlString, String.class, content);
    }

    public String queryShareholderCompany(String content) {
        String sqlString = "SELECT company_name FROM shareholder_info WHERE shareholder_name = ?;";
        return queryForObjectOrNull(sqlString, String.class, content);
    }
}
