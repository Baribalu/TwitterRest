package com.cgm.twitter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cgm.entities.Friend;
import com.cgm.twitter.services.UserService;

@Controller
@RequestMapping("friends")
public class FriendsController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String friends(ModelMap model, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
		model.addAttribute("friends",userService.getFriends(username));
		model.put("newFriend", new Friend());
		return "friends";
	}
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	public String addFriend(@ModelAttribute("newFriend")Friend friend,ModelMap model, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
		userService.addUser(username,friend.getUsername());
		return "redirect:/friends";
	}
	
	@RequestMapping(value="remove",method = RequestMethod.POST)
	public String removeFriend(@ModelAttribute("newFriend") Friend friend,ModelMap model, HttpServletRequest request) {
		String username = (String)request.getSession().getAttribute("username");
		userService.removeUser(username,friend.getUsername());
		return "redirect:/friends";
	}
	
}
