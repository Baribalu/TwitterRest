package com.cgm.twitter.services;

import java.util.ArrayList;

import com.cgm.entities.Account;
import com.cgm.entities.Friend;
import com.cgm.entities.Message;

public interface UserService {

	public Account getAccount(Account account);
	public ArrayList<Message> getMessages(String username);
	public void postMessage(String username, Message message);
	public ArrayList<Message> getFollowingMessages(String username);
	public ArrayList<Friend> getFriends(String username);
	public void addUser(String username,String user);
	public void removeUser(String username,String user);
	
}
