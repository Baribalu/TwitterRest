package com.cgm.twitter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cgm.twitter.domain.User;
import com.cgm.twitter.dto.ServiceResponse;
import com.cgm.twitter.services.UserService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		return new ModelAndView("login");
	}

	@RequestMapping(method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public @ResponseBody ServiceResponse login(@RequestBody User account, ModelMap model, HttpServletRequest request) {
		User myAccount = userService.getAccount(account);
		ServiceResponse response = new ServiceResponse();
		response.setCode(200);
		if (myAccount != null) {
			request.getSession().setAttribute("username", account.getUsername());
			return response;
		}
		else {
			response.setCode(202);
			response.setMessage("Bad Account!");
			return response;
		}
		
	}

}
