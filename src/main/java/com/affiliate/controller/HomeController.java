package com.affiliate.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.bean.User;
import com.affiliate.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class HomeController {

	@Autowired
	private UserRepository repo;
	
	@GetMapping("/home")
	public ModelAndView home(ModelAndView modelAndView, Principal principal)throws NumberFormatException {

		modelAndView.setViewName("users/business-panel-dashboard1");
		User u=repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details",u);
		//System.out.println("user id: "+u.getUserid());

		return modelAndView;
	}
	@RequestMapping("/contact-us")
	public String contactUs() {
		
		return "users/contact-us";
	}
	
	@RequestMapping("/forget-password")
	public ModelAndView forgetPassword(ModelAndView modelAndView) {
		modelAndView.setViewName("users/forget-password");
		return modelAndView;
	}
	@RequestMapping("/notification-details")
	public ModelAndView notificationDetails(ModelAndView modelAndView) {
		modelAndView.setViewName("users/notification-details");
		return modelAndView;
	}
	
	@RequestMapping("/notification")
	public ModelAndView notification(ModelAndView modelAndView) {
		modelAndView.setViewName("users/notification");
		return modelAndView;
	}
	
	@RequestMapping("/payment")
	public ModelAndView payment(ModelAndView modelAndView) {
		modelAndView.setViewName("users/payment");
		return modelAndView;
	}
	
	@RequestMapping("/privacy-policy")
	public ModelAndView privacyPolicy(ModelAndView modelAndView) {
		modelAndView.setViewName("users/privacy-policy");
		return modelAndView;
	}
	

	@RequestMapping("/profile-details/{userid}")
	public ModelAndView profileDetails(@PathVariable (name="userid")String userid,ModelAndView modelAndView,Principal principal) throws Exception{
		System.out.println("this is profile");
		User u=repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details",u);
		modelAndView.setViewName("users/profile-details");
		
		return modelAndView;
	}
	@RequestMapping("/updateUser/{userid}")
	public ModelAndView showEditStudentPage(@ModelAttribute User user,ModelAndView modelAndView ) {
		User savedUser=(User)repo.save(user);
		
		return modelAndView;
	}
	
	
	@RequestMapping("/sales-by-affiliate")
	public ModelAndView salesByAffiliate(ModelAndView modelAndView) {
		modelAndView.setViewName("users/sales_by_affiliate");
		return modelAndView;
	}
	@RequestMapping("/settings")
	public ModelAndView settings(ModelAndView modelAndView) {
		modelAndView.setViewName("users/settings");
		return modelAndView;
	}
	
	@RequestMapping("/term-condition")
	public ModelAndView termsAndConditions(ModelAndView modelAndView) {
		modelAndView.setViewName("users/term-condition");
		return modelAndView;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(ModelAndView modelAndView) {
		modelAndView.setViewName("users/login");
		return modelAndView;
	}
	
	

	
	
	
}
