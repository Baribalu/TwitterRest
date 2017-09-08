package com.cgm.twitter.controllers;

import java.awt.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.entities.Friend;
import com.cgm.twitter.services.UserService;

@RestController
@RequestMapping("friends")
public class FriendsController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView friends(ModelMap model) {
		return new ModelAndView("friends");
	}
	
	@RequestMapping(value="/getFriends" ,method = RequestMethod.GET)
	public ArrayList<Friend> getFriends(HttpServletRequest request){
		ArrayList<Friend> friends = userService.getFriends((String)request.getSession().getAttribute("username"));
		return friends;
	}
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	public @ResponseBody Friend addFriend(@RequestBody Friend friend,ModelMap model, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
		userService.addUser(username,friend.getUsername());
		return friend;
	}
	
	@RequestMapping(value="remove",method = RequestMethod.POST)
	public @ResponseBody Friend removeFriend(@RequestBody Friend friend,ModelMap model, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
		userService.removeUser(username,friend.getUsername());
		return friend;
	}
	
}
