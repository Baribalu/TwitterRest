package com.cgm.twitter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.entities.Account;
import com.cgm.twitter.services.UserService;

@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String login(ModelMap model, HttpServletRequest request) {
		model.put("account", new Account());
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String login(@ModelAttribute("account") Account account, ModelMap model, HttpServletRequest request) {
		Account myAccount = userService.getAccount(account);
		if (myAccount != null) {
			request.getSession().setAttribute("username", account.getUsername());
			return "redirect:home";
		}
		else {
			model.addAttribute("error","Bad Account!");
			return "login";
		}
		
	}

}
