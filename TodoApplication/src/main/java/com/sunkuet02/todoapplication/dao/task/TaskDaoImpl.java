package com.sunkuet02.todoapplication.dao.task;

import com.sunkuet02.todoapplication.dao.user.UserDaoImpl;
import com.sunkuet02.todoapplication.models.Task;
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
 * Created by sun on 3/2/17.
 */
@Repository
public class TaskDaoImpl implements TaskDao {

    final static Logger logger = Logger.getLogger(UserDaoImpl.class);

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Task> getAllTask(String username) {
        String sql = "SELECT * FROM tasks WHERE username=:username";

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        return namedParameterJdbcTemplate.query(sql, params, new TaskMapper());
    }

    @Override
    public int addTask(Task task) {
        String sql = "INSERT INTO tasks (username, heading, description, time) VALUES (:username, :heading, :description, :time)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, getSqlParameterSourceFromUser(task), keyHolder);
        task.setId(keyHolder.getKey().intValue());

        return task.getId();
    }

    @Override
    public List<Task> search(String username, String text) {
        String sql = "SELECT * FROM tasks WHERE username=:username AND (heading LIKE '%"+text+"%' OR description LIKE '%"+text+"%')";

        Map<String, Object> params = new HashMap<>();
        params.put("username", username);

        return namedParameterJdbcTemplate.query(sql, params, new TaskMapper());
    }

    private SqlParameterSource getSqlParameterSourceFromUser(Task task) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("id", task.getId());
        mapSqlParameterSource.addValue("username", task.getUsername());
        mapSqlParameterSource.addValue("heading", task.getHeading());
        mapSqlParameterSource.addValue("description", task.getDescription());
        mapSqlParameterSource.addValue("time", task.getTime());
        return mapSqlParameterSource;
    }

    private static final class TaskMapper implements RowMapper<Task> {

        @Override
        public Task mapRow(ResultSet resultSet, int i) throws SQLException {
            Task task = new Task();
            task.setId(resultSet.getInt("id"));
            task.setUsername(resultSet.getString("username"));
            task.setHeading(resultSet.getString("heading"));
            task.setDescription(resultSet.getString("description"));
            task.setTime(resultSet.getLong("time"));

            return task;
        }
    }
}
