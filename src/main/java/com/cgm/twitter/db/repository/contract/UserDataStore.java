package com.cgm.twitter.db.repository.contract;


import java.util.List;

import com.cgm.twitter.domain.User;

public interface UserDataStore {

	void StoreUser(User user);
	
	List<User> readUser();
	
}
