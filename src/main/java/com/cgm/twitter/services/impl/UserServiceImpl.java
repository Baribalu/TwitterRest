package com.cgm.twitter.services.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.cgm.builder.AccountBuilder;
import com.cgm.entities.Account;
import com.cgm.entities.Friend;
import com.cgm.entities.Message;
import com.cgm.twitter.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Account getAccount(Account account) {
		Account myAccount = null;
		for (Account accountElement : AccountBuilder.accounts) {
			if (accountElement.getPassword().equals(account.getPassword())
					&& accountElement.getUsername().equals(account.getUsername())) {
				myAccount = accountElement;
			}
		}
		return myAccount;
	}

	@Override
	public ArrayList<Message> getMessages(String username) {
		ArrayList<Message> messages = AccountBuilder.messages.get(username);
		return messages;
	}

	@Override
	public ArrayList<Message> getFollowingMessages(String username) {
		ArrayList<String> following = AccountBuilder.following.get(username);
		ArrayList<Message> allMessages = new ArrayList<Message>();
		allMessages.addAll(getMessages(username));
		for (String user : following) {
			ArrayList<Message> userMessages = AccountBuilder.messages.get(user);
			allMessages.addAll(userMessages);
		}
		return allMessages;
	}

	@Override
	public void postMessage(String username, Message message) {
		ArrayList<Message> messages = AccountBuilder.messages.get(username);
		messages.add(message);
	}

	@Override
	public ArrayList<Friend> getFriends(String username) {
		ArrayList<String> friendsUsername = new ArrayList<String>();
		ArrayList<Friend> friends = new ArrayList<Friend>();
		Friend newFriend;
		friendsUsername = AccountBuilder.following.get(username);
		for (Account account : AccountBuilder.accounts) {
			newFriend = new Friend(account.getUsername(), account.getFullName(), account.getAge(), true);
			for (String friend : friendsUsername) {
				if (account.getUsername().equals(friend)) {
					friends.add(newFriend);
				}
			}
			if(friends.indexOf(newFriend) == -1 && (!account.getUsername().equals(username))) {
				friends.add(new Friend(account.getUsername(),account.getFullName(),account.getAge(),false));
			}
		}
		
		return friends;
	}
	
	@Override
	public void addUser(String username,String user) {
		AccountBuilder.following.get(username).add(user);
	}
	
	@Override
	public void removeUser(String username,String user) {
		AccountBuilder.following.get(username).remove(user);
	}

}
