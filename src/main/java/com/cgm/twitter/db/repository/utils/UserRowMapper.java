package com.cgm.twitter.db.repository.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cgm.twitter.domain.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int line) throws SQLException{
		UserResultSetExtractor extractor = new UserResultSetExtractor();
		return extractor.extractData(rs);
	}
	
}
