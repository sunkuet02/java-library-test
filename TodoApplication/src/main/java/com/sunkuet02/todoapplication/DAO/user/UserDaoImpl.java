package com.sunkuet02.todoapplication.dao.user;

import com.sunkuet02.todoapplication.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sun on 2/28/17.
 */
@Repository
public class UserDaoImpl implements UserDao {

    final static Logger logger = Logger.getLogger(UserDaoImpl.class);

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO users (username, password, email) VALUES (:username, :password, :email)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromUser(user), keyHolder);
        user.setId(keyHolder.getKey().intValue());

        return user.getId();
    }

    @Override
    public User findUser(String username) {
        String sql = "SELECT * FROM users WHERE username=:username";

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        List<User> users =  namedParameterJdbcTemplate.query(sql, params, new UserMapper());

        return users.size() == 0 ? new User():users.get(0);
    }

    private SqlParameterSource getSqlParameterSourceFromUser(User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", user.getId());
        mapSqlParameterSource.addValue("username", user.getUsername());
        mapSqlParameterSource.addValue("email", user.getEmail());
        mapSqlParameterSource.addValue("password", user.getPassword());
        return mapSqlParameterSource;
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));;
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));

            return user;
        }
    }
}
