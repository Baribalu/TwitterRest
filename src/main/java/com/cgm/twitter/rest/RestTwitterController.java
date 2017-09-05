package com.cgm.twitter.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.builder.AccountBuilder;
import com.cgm.entities.Account;
import com.cgm.entities.Friend;
import com.cgm.entities.Message;
import com.cgm.twitter.services.UserService;

@RestController
public class RestTwitterController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/getMessages", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Message> getMessages(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String username = (String) request.getSession().getAttribute("username");
		ArrayList<Message> messages = null;
		if (username != null) {
			messages = userService.getFollowingMessages(username);
		}
		return messages;
	}

	@RequestMapping(value = "/addMessage", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public @ResponseBody Message addMessage(@RequestBody Message message, HttpServletRequest request) throws Exception {
		String username = (String) request.getSession().getAttribute("username");
		message.setUser(username);
		userService.postMessage(username, message);
		return message;
	}

	@RequestMapping(value="/search/{user}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Friend search(@PathVariable("user") String user, HttpServletRequest request) throws Exception{
		String username = (String) request.getSession().getAttribute("username");
		for(Account account:AccountBuilder.accounts) {
			if(user.equals(account.getUsername())) {
				if(AccountBuilder.following.get(username).indexOf(user) != -1 ) {
					return new Friend(account.getUsername(),account.getFullName(),account.getAge(),true);
				}
				else
				{
					return new Friend(account.getUsername(),account.getFullName(),account.getAge(),false);
				}
			}
		}
		return null;
		
	}

}
