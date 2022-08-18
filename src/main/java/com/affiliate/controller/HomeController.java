package com.affiliate.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.support.DefaultCrudMethods;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.bean.User;
import com.affiliate.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class HomeController{

	
	@Autowired
	private BCryptPasswordEncoder bp;
	
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
	public ModelAndView notificationDetails(ModelAndView modelAndView, Principal principal) {
		User u=repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details",u);
		modelAndView.setViewName("users/notification-details");
		return modelAndView;
	}
	
	@RequestMapping("/notification")
	public ModelAndView notification(ModelAndView modelAndView,Principal principal) {
		User u=repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details",u);
		modelAndView.setViewName("users/notification");
		return modelAndView;
	}
	
	@RequestMapping("/payment")
	public ModelAndView payment(ModelAndView modelAndView,Principal principal) {
		User u=repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details",u);
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
	@RequestMapping("/updateUser")
	public String showEditStudentPage(User user, Model model) {
		//User u=repo.findByEmail("");
		if(user.getUserid()==null) {
			throw new UsernameNotFoundException("user not found");
		}else {
			repo.save(user);
			System.out.println("userid "+user.getUserid());
			System.out.println("user updated");
			//user.setPassword(bp.encode(user.getPassword()));
			//user.setRole("ROLE_USER");
		
			
			
		}
		
		return "";
	}
	
	
	@RequestMapping("/sales-by-affiliate")
	public ModelAndView salesByAffiliate(ModelAndView modelAndView) {
		
		modelAndView.setViewName("users/sales_by_affiliate");
		return modelAndView;
	}
	@RequestMapping("/settings")
	public String settings(Principal principal,Model model) {
		User u=repo.findByEmail(principal.getName());
		model.addAttribute("user_details",u);
		return "users/mysetting";
	}
	
	@RequestMapping("/term-condition")
	public String termsAndConditions(Model model) {
	
		return "users/term-condition";
	}

	@RequestMapping("/myaffiliate")
	public String myAffiliate(Model model, Principal principal) {
		User u=repo.findByEmail(principal.getName());
		model.addAttribute("user_details",u);
		return "users/my-affiliate";
}
	
	
	
	public String myUsername(Principal principal) {
		User u=repo.findByEmail(principal.getName());
		String name = u.getFirstname();
		return name;
	}
	
}
