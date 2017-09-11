package com.cgm.twitter.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgm.twitter.domain.User;
import com.cgm.twitter.repository.AbstractDAO;
import com.cgm.twitter.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDAO")
	AbstractDAO<User> userDAO;

	@Override
	public User getAccount(User account) {
		User user=null;
		try {
			user = userDAO.findByUsername(account.getUsername());
			if(user.getPassword().equals(account.getPassword())) {
				return user;
			}else {
				return null;
			}
		} catch (NoResultException ex) {
			return user;
		}
	}


	@Override
	public User findUser(Long id) {
		return userDAO.findById(id);
	}
	
	@Override
	public List<User> getFriends(String username) {
		User user = userDAO.findByUsername(username);
		List<User> friends = user.getUser();
		
		return friends;
	}
	
	@Override
	public List<User> getUsers(String username){
		User user = userDAO.findByUsername(username);
		List<User> friends = user.getUser();
		List<User> users = userDAO.findAllUsers(username);
		Iterator<User> iter = users.iterator();
			for(User friend:friends) {
				String userna = iter.next().getUsername();
				if(friend.getUsername().equals(userna)) {
					iter.remove();
				}
		}
		return users;
	}
	
	@Override
	public void addUser(String username,String newFriend) {
		User user = userDAO.findByUsername(username);
		User newUser = userDAO.findByUsername(newFriend);
		userDAO.addFriend(user, newUser);
	}
	
	@Override
	public void removeUser(String username, String friend) {
		User user = userDAO.findByUsername(username);
		User newUser = userDAO.findByUsername(friend);
		userDAO.removeFriend(user, newUser);
	}

}
