package com.cgm.twitter.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgm.twitter.domain.Message;
import com.cgm.twitter.domain.User;
import com.cgm.twitter.repository.AbstractDAO;
import com.cgm.twitter.services.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	@Qualifier("userDAO")
	AbstractDAO<User> userDAO;
	
	@Autowired
	@Qualifier("messageDAO")
	AbstractDAO<Message> messageDAO;
	
	@Override
	public void postMessage(Message message, String username) {
		User user = userDAO.findByUsername(username);
		message.setUser(user);
		messageDAO.update(message);
	}
	
	@Override 
	public List<Message> getMessages(String username){
		User user = userDAO.findByUsername(username);
		List<Message> messages = messageDAO.findAllMessages(user);
		messages = user.getMessages();
		return messages;
	}
	
}
