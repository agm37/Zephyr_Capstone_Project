package capstone.zephyr.zephyr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private static LoginModel loginModelRowMapper(ResultSet rs, int rowNum) throws SQLException {
        Optional<Integer> shareholderID = Optional.of(rs.getInt(5));
        if (rs.wasNull()) {
            shareholderID = Optional.empty();
        }

        return new LoginModel(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4),
                                shareholderID);
    }

    public LoginModel queryLoginByUserName(String userName) {
        String sqlString = "SELECT company_id, user_name, hashed_password, is_admin, shareholder_id FROM company_logins WHERE user_name = ?;";
        return queryForObjectOrNull(sqlString, DatabaseAccess::loginModelRowMapper, userName);
    }

    public List<LoginModel> queryAllLogins() {
        String sqlString = "SELECT company_id, user_name, hashed_password, is_admin, shareholder_id FROM company_logins;";
        return databaseTemplate.query(sqlString, DatabaseAccess::loginModelRowMapper);
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

    //****Shareholder Poll Status Select/Insert Queries****\\

    public boolean queryHasShareholderVotedInPoll(int shareholderID, int pollID) {
        String sqlString = "SELECT * FROM shareholder_votes WHERE shareholder_id = ? AND poll_id = ?;";
        SqlRowSet rowSet = databaseTemplate.queryForRowSet(sqlString, shareholderID, pollID);
        return rowSet.next();
    }

    private void setShareholderVoteStatus(int shareholderID, int pollID) {
        String sqlString = "INSERT INTO shareholder_votes (shareholder_id, poll_id) VALUES (?, ?)";
        databaseTemplate.update(sqlString, shareholderID, pollID);
    }


    //****Vote Information Queries (Getters)****\\

    public boolean queryIsPollClosed(int pollID) {
        String sqlString = "SELECT is_closed FROM vote_info WHERE poll_id = ?";

        Integer result = queryForObjectOrNull(sqlString, Integer.class, pollID);
        return result != null && result == 1;
    }

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

    private void updateVotes(int pollID, int voteParameterNum, int voteCount) {
        String sqlString = "UPDATE vote_count SET vote_count_" + voteParameterNum + " = COALESCE(vote_count_" + voteParameterNum + ", 0) + ? WHERE poll_id = ?;";
        databaseTemplate.update(sqlString, voteCount, pollID);
    }

    @Transactional
    public void recordShareholderVote(int shareholderID, int pollID, int voteParameterNum) {
        int voteCount = queryShareholderShares(shareholderID);
        updateVotes(pollID, voteParameterNum, voteCount);
        setShareholderVoteStatus(shareholderID, pollID);
    }

    //****Poll Creation Query (Initial Setter)****\\

    @Transactional
    public Boolean createPoll(String pollName, String companyName, ArrayList<String> parameterValues) {
        Optional<Integer> pollID = insertPollInfo(pollName, companyName);
        if (pollID.isEmpty()) {
            return false;
        }

        insertVoteParameters(pollID.get(), parameterValues);
        return true;
    }

    public boolean closePoll(int pollID) {
        String sqlString = "UPDATE vote_info SET is_closed = 1 WHERE poll_id = ?";
        int rowsAffected = databaseTemplate.update(sqlString, pollID);
        return rowsAffected == 1;
    }

    private Optional<Integer> insertPollInfo(String pollName, String companyName) {
        String sqlString = "INSERT INTO vote_info (poll_name, company_name) VALUES (?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rowsAffected = databaseTemplate.update(
            (Connection connection) -> {
                PreparedStatement sqlInsert = connection.prepareStatement(
                    sqlString, Statement.RETURN_GENERATED_KEYS);
                sqlInsert.setString(1, pollName);
                sqlInsert.setString(2, companyName);
                return sqlInsert;
            },
            keyHolder);
        if (rowsAffected != 1) {
            System.out.println("UPDATE FAILED, RETURNED ROWS AFFECTED " + rowsAffected);
            return Optional.empty();
        }

        return Optional.of(keyHolder.getKey().intValue());
    }

    private void insertVoteParameters(int pollID, ArrayList<String> parameterValues) {
        String sqlString = "INSERT INTO vote_count (poll_id, parameter_name_1, parameter_name_2, parameter_name_3, parameter_name_4, parameter_name_5, parameter_name_6, parameter_name_7, parameter_name_8, parameter_name_9, parameter_name_10) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        databaseTemplate.execute(sqlString, (PreparedStatement sqlInsert) -> {
            int nextIndex = 1;
            sqlInsert.setInt(nextIndex++, pollID);
            for (int i = 0; i < parameterValues.size(); i++) {
                sqlInsert.setString(nextIndex++, parameterValues.get(i));
            }
            for (int i = parameterValues.size(); i < 10; i++) {
                sqlInsert.setString(nextIndex++, "");
            }
            return sqlInsert.execute();
        });
    }
}
