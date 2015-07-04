package com.nibrahimli.backoffice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LonginController {

	@RequestMapping(value="/")
	public String home(ModelAndView mav) {
		return "home";
	}
	
	@RequestMapping(value="/login")
	public String login(ModelAndView mav) {
		return "login";
	}
	
	@RequestMapping(value="/loginfailed")
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";
	}
}
