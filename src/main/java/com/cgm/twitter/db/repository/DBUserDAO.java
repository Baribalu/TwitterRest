package com.cgm.twitter.db.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.cgm.twitter.db.repository.contract.UserDataStore;
import com.cgm.twitter.db.repository.utils.UserRowMapper;
import com.cgm.twitter.domain.User;

public class DBUserDAO implements UserDataStore {

	private SimpleJdbcInsert insertUser;
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.insertUser = new SimpleJdbcInsert(dataSource).withTableName("users").usingGeneratedKeyColumns("id");
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	@Transactional
	public void StoreUser(User user) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("username",user.getUsername());
		try {
			insertUser.execute(parameters);
		}catch(DataAccessException ex) {
			System.out.println("A database error occured" + ex.getMessage());
		}
	}

	@Override
	public List<User> readUser() {
		StringBuilder selectStatement = new StringBuilder();
		selectStatement.append("SELECT * FROM ").append(
				"users");

		List<User> users = null;
		try {
			users = jdbcTemplate.query(selectStatement.toString(),
					new Object[] { }, new UserRowMapper());
		} catch (DataAccessException dae) {
			System.out.println("A database error occured: " + dae.getMessage());
		}

		return users;
	}

	
	
}
