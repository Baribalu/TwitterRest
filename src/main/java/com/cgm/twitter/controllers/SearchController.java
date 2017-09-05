package com.cgm.twitter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cgm.builder.AccountBuilder;
import com.cgm.entities.Account;
import com.cgm.entities.Friend;
import com.cgm.twitter.services.UserService;

@RestController
@RequestMapping("/search")
public class SearchController {

	@Autowired
	UserService userService;
	
	@RequestMapping(value="/{user}", method = RequestMethod.GET, produces = "application/json")
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
