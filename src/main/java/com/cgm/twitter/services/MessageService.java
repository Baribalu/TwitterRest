package com.cgm.twitter.services;

import java.util.List;

import com.cgm.twitter.domain.Message;

public interface MessageService {

	void postMessage(Message message, String username);
	public List<Message> getMessages(String username);
	
}
