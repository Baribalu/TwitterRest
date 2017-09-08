package com.cgm.twitter.repository;

import org.springframework.stereotype.Repository;

import com.cgm.twitter.domain.User;

@Repository
public class UserDAO extends AbstractDAO<User> {

	protected UserDAO() {
		super(User.class);
	}
	
}
