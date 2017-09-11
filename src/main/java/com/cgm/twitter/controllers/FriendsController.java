package com.cgm.twitter.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.cgm.twitter.domain.User;
import com.cgm.twitter.services.UserService;

@RestController
@RequestMapping("friends")
public class FriendsController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView Users(ModelMap model) {
		return new ModelAndView("friends");
	}
	
	@RequestMapping(value="/getFriends" ,method = RequestMethod.GET)
	public List<User> getFriends(HttpServletRequest request){
		List<User> friends = userService.getFriends((String)request.getSession().getAttribute("username"));
		return friends;
	}
	
	@RequestMapping(value="/getUsers", method = RequestMethod.GET)
	public List<User> getUSers(HttpServletRequest request){
		String username = (String)request.getSession().getAttribute("username");
		List<User> users = userService.getUsers(username);
		return users;
	}
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	public @ResponseBody User addUser(@RequestBody User User,ModelMap model, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
//		userService.addUser(username,User.getUsername());
		return User;
	}
	
	@RequestMapping(value="remove",method = RequestMethod.POST)
	public @ResponseBody User removeUser(@RequestBody User user,ModelMap model, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
//		userService.removeUser(username,User.getUsername());
		return user;
	}
	
}
