package com.cgm.twitter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("logout")
public class LogoutController {

	@RequestMapping(method=RequestMethod.GET)
	public String logout(ModelMap modelMap) {

		return "logout";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String logout(HttpServletRequest request,ModelMap modelMap) {
		request.getSession().removeAttribute("username");
		return "redirect:home";
	}
	
}
