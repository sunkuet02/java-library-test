package com.sunkuet02.spring.dao;

import com.sunkuet02.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
 * Created by sun on 2/22/17.
 */

@Repository
public class UserDaoImpl implements UserDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";

        return namedParameterJdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public User getUser(int id) {
        String sql = "SELECT * FROM users WHERE id=:id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        return namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
    }

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO users (name, password, email) VALUES (:name, :password, :email)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromUser(user), keyHolder);
        user.setId(keyHolder.getKey().intValue());

        return user.getId();
    }

    @Override
    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id=:id";

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);

        namedParameterJdbcTemplate.update(sql, params);
    }

    private SqlParameterSource getSqlParameterSourceFromUser(User user) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", user.getId());
        mapSqlParameterSource.addValue("name", user.getName());
        mapSqlParameterSource.addValue("email", user.getEmail());
        mapSqlParameterSource.addValue("password", user.getPassword());
        return mapSqlParameterSource;
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("id"));;
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));

            return user;
        }
    }
}
