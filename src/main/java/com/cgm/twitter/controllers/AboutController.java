package com.cgm.twitter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("about")
public class AboutController {

	@RequestMapping(method = RequestMethod.GET)
	public String getAbout(ModelMap model) {
		
		return "about";
	}
	
}
