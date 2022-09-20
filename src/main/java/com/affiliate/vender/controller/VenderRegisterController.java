package com.affiliate.vender.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VenderRegisterController {

	
	public ModelAndView venderRegistration(ModelAndView modelAndView) {
		
		modelAndView.setViewName("vender/registration-form");
		return modelAndView;
	}
	
}
