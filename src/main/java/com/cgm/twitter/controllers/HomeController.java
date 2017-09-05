package com.cgm.twitter.controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.builder.AccountBuilder;
import com.cgm.entities.Account;
import com.cgm.entities.Message;
import com.cgm.twitter.services.UserService;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(ModelMap model) {
		model.put("message", new Message());
		return new ModelAndView("home");
	}

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
		userService.postMessage(message.getUser(), message);
		return message;
	}
	
	

}
