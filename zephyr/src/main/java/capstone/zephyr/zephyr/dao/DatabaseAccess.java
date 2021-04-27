package capstone.zephyr.zephyr.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import capstone.zephyr.zephyr.model.LoginModel;

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

    private <T> T queryForObjectOrNull(String sql, RowMapper<T> rowMapper, Object... args)
            throws DataAccessException {
        try {
            return databaseTemplate.queryForObject(sql, rowMapper, args);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }


    //****Login and Authentication Queries (Getters)****\\

    private RowMapper<LoginModel> getLoginModelRowMapper() {
        return (ResultSet rs, int rowNum) ->
            new LoginModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4));
    }

    public LoginModel queryLoginByUserName(String userName) {
        String sqlString = "SELECT company_id, user_name, hashed_password, is_admin FROM company_logins WHERE user_name = ?;";
        return queryForObjectOrNull(sqlString, getLoginModelRowMapper(), userName);
    }

    public List<LoginModel> queryAllLogins() {
        String sqlString = "SELECT company_id, user_name, hashed_password, is_admin FROM company_logins;";
        return databaseTemplate.query(sqlString, getLoginModelRowMapper());
    }

    public int queryAdminStatus(String userName) {
        String sqlString = "SELECT is_admin FROM company_logins WHERE user_name = ?;";
        return queryForObjectOrNull(sqlString, Integer.class, userName);
    }

    
    //****Login Insert/Update Queries (Setters)****\\

    public void updateLoginPasswordById(int companyID, String newPassword) {
        String sqlString = "UPDATE company_logins SET hashed_password = ? WHERE company_id = ?";
        databaseTemplate.update(sqlString, newPassword, companyID);
    }


    //****Shareholder Information Queries (Getters)****\\

    public String queryShareholderName(int shareHolderID) {
        String sqlString = "SELECT shareholder_name FROM shareholder_info WHERE shareholder_id = ?;";
        return queryForObjectOrNull(sqlString, String.class, shareHolderID);
    }

    public String queryShareholderCompany(int shareHolderID) {
        String sqlString = "SELECT company_name FROM shareholder_info WHERE shareholder_id = ?;";
        return queryForObjectOrNull(sqlString, String.class, shareHolderID);
    }

    public int queryShareholderShares(int shareHolderID) {
        String sqlString = "SELECT num_shares FROM shareholder_info WHERE shareholder_id = ?;";
        return queryForObjectOrNull(sqlString, Integer.class, shareHolderID);
    }

    public int queryShareholderEligibility(int shareholderID) {
        String sqlString = "SELECT has_voted FROM shareholder_info WHERE shareholder_id = ?;";
        return queryForObjectOrNull(sqlString, Integer.class, shareholderID);
    }
    

    //****Shareholder Insert/Update Queries (Setters)****\\

    public void updateShareholderEligibility(int shareholderID) {
        String sqlString = "UPDATE shareholder_info SET has_voted = 1 WHERE shareholder_id = ?;";
        databaseTemplate.update(sqlString, shareholderID);
    }

    
    //****Vote Information Queries (Getters)****\\

    public ArrayList<String> queryVoteParameter(int pollID) {
        String sqlString = "SELECT parameter_name_1, parameter_name_2, parameter_name_3, parameter_name_4, parameter_name_5, parameter_name_6, parameter_name_7, parameter_name_8, parameter_name_9, parameter_name_10 FROM vote_count WHERE poll_id = ?;";

        return queryForObjectOrNull(sqlString, (ResultSet rs, int rowNum) -> {
            ArrayList<String> results = new ArrayList<String>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                results.add(rs.getString(i));
            }
            return results;
        }, pollID);
    }

    public ArrayList<Integer> queryVoteCount(int pollID) {
        String sqlString = "SELECT vote_count_1, vote_count_2, vote_count_3, vote_count_4, vote_count_5, vote_count_6, vote_count_7, vote_count_8, vote_count_9, vote_count_10 FROM vote_count WHERE poll_id = ?;";

        return queryForObjectOrNull(sqlString, (ResultSet rs, int rowNum) -> {
            ArrayList<Integer> results = new ArrayList<Integer>();
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                results.add(rs.getInt(i));
            }
            return results;
        }, pollID);
    }

    public String queryComapanyName(int pollID) {
        String sqlString = "SELECT company_name FROM vote_info WHERE poll_id = ?;";
        return queryForObjectOrNull(sqlString, String.class, pollID);
    }

    public int queryNumberShareholders(int pollID) {
        String sqlString = "SELECT num_shareholders FROM vote_info WHERE poll_id = ?;";
        return queryForObjectOrNull(sqlString, Integer.class, pollID);
    }

    public int queryNumberCastVotes(int pollID) {
        String sqlString = "SELECT num_cast_votes FROM vote_info WHERE poll_id = ?;";
        return queryForObjectOrNull(sqlString, Integer.class, pollID);
    }

    public int queryNumberVotesRemaining(int pollID) {
        String sqlString = "SELECT num_votes_remaining FROM vote_info WHERE poll_id = ?;";
        return queryForObjectOrNull(sqlString, Integer.class, pollID);
    }


    //****Vote Insert/Update Queries (Setters)****\\

    public void updateVotes(int pollID, int voteParameterNum, int voteCount) {
        String sqlString = "UPDATE vote_count SET vote_count_" + voteParameterNum + " = ? WHERE poll_id = ?;";
        databaseTemplate.update(sqlString, voteCount, pollID);
    }

    public int getNewPollID() {
        int newPollID = (queryForObjectOrNull("SELECT poll_id FROM vote_info WHERE poll_id = (SELECT max(poll_id) FROM vote_info);", Integer.class)) + 1;
        return newPollID;
    }


    //****Poll Creation Query (Initial Setter)****\\

    public Boolean createPoll(String pollName, String companyName, int pollID) {
        String sqlString = "INSERT INTO vote_info (poll_id, poll_name, company_name) VALUES ?,?,?;";

        return databaseTemplate.execute(sqlString, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement sqlInsert) throws SQLException, DataAccessException {
                sqlInsert.setInt(1, pollID);
                sqlInsert.setString(2, pollName);
                sqlInsert.setString(3, companyName);
                return sqlInsert.execute();            
            }
        });
    }

    public Boolean createPollCount(int pollID) {
        String sqlString = "INSERT INTO vote_count (poll_id) VALUES ?;";

        return databaseTemplate.execute(sqlString, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement sqlInsert) throws SQLException, DataAccessException {
                sqlInsert.setInt(1, pollID);
                return sqlInsert.execute();
            }
        });
    }

    public Boolean setVoteParameters(int pollID, ArrayList<String> parameterValues) {
        String sqlString = "INSERT INTO vote_count (parameter_name_1, parameter_name_2, parameter_name_3, parameter_name_4, parameter_name_5, parameter_name_6, parameter_name_7, parameter_name_8, parameter_name_9, parameter_name_10) VALUES ?,?,?,?,?,?,?,?,?,? WHERE poll_id = " + pollID + ";";

        return databaseTemplate.execute(sqlString, new PreparedStatementCallback<Boolean>() {
            @Override
            public Boolean doInPreparedStatement(PreparedStatement sqlInsert) throws SQLException, DataAccessException {
                for (int i = 0; i < parameterValues.size(); i++) {
                    sqlInsert.setString((i + 1), parameterValues.get(i));
                }
                return sqlInsert.execute();
            }
        });
    }
}
