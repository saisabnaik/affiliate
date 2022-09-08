package com.affiliate.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminRegisterController {

	
	public ModelAndView adminRegistration(ModelAndView modelAndView) {
		
		modelAndView.setViewName("super-admin/registration-form");
		return modelAndView;
	}
	
}
