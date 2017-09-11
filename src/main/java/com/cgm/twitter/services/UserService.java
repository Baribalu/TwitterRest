package com.cgm.twitter.services;

import java.util.ArrayList;
import java.util.List;

import com.cgm.twitter.domain.User;

public interface UserService {

	public User getAccount(User account);
	public User findUser(Long id);
	
	public List<User> getFriends(String username);
	public List<User> getUsers(String username);
	
	public void addUser(String username,String newFriend);
	public void removeUser(String username, String friend);
	
}
