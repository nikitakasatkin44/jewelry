package com.ngkasatkin.dao;

import com.ngkasatkin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
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

@Repository
public class UserDaoImpl implements UserDao {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) throws DataAccessException {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public User findById(Integer id) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);

        String sql = "SELECT * FROM users WHERE id=:id";

        User result = null;
        try {
            result = namedParameterJdbcTemplate.queryForObject(sql, params, new UserMapper());
        } catch (EmptyResultDataAccessException e) {}

        return result;
    }

    public List<User> findAll() {

        String sql = "SELECT * FROM users";
        List<User> result = namedParameterJdbcTemplate.query(sql, new UserMapper());

        return result;
    }

    public void save(User user) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO USERS(LOGIN, PASSWORD, FIRST_NAME, LAST_NAME, EMAIL, DESCRIPTION) "
                + "VALUES ( :login, :password, :first_name, :last_name, :email, :description)";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user), keyHolder);
        user.setId(keyHolder.getKey().intValue());
    }

    public void update(User user) {

        String sql = "UPDATE USERS SET LOGIN=:login, PASSWORD=:password, FIRST_NAME=:first_name, LAST_NAME=:last_name, " +
                "EMAIL=:email, DESCRIPTION=:description WHERE id=:id";

        namedParameterJdbcTemplate.update(sql, getSqlParameterByModel(user));
    }

    public void delete(Integer id) {

        String sql = "DELETE FROM USERS WHERE id=:id";
        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
    }

    private SqlParameterSource getSqlParameterByModel(User user) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", user.getId());
        parameterSource.addValue("login", user.getLogin());
        parameterSource.addValue("password", user.getPassword());
        parameterSource.addValue("first_name", user.getFirst_name());
        parameterSource.addValue("last_name", user.getLast_name());
        parameterSource.addValue("email", user.getEmail());
        parameterSource.addValue("description", user.getDescription());

        return parameterSource;
    }

    private static final class UserMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setDescription(rs.getString("description"));

            return user;


        }

    }



}
