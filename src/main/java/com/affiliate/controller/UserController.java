package com.affiliate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.affiliate.bean.MyUser;
import com.affiliate.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder bp;
	
	@RequestMapping("/")
	public String login() {
		
		return "users/login";
	}
	
	@RequestMapping("/registerUser")
	public String registration() {
		
		return "users/registration-form";
		
	}

	
	@PostMapping("/addNewUser")
	public String registerUser(@ModelAttribute MyUser myuser,  RedirectAttributes redirect,Model model) throws Exception {
		if(myuser.getFirstname()==null || myuser.getFirstname().isEmpty()) {
			redirect.addFlashAttribute("message", "firstname cannot be blank");
			return "redirect:/registerUser";
		}
		if(myuser.getLastname()==null || myuser.getLastname().isEmpty()) {
			redirect.addFlashAttribute("message", "Last Name cannot be blank");
			return "redirect:/registerUser";
		}
		if(myuser.getEmail()==null || myuser.getEmail().isEmpty()) {
			redirect.addFlashAttribute("message", "email cannot be blank");
			return "redirect:/registerUser";
		}
		if(myuser.getPassword()==null || myuser.getPassword().isEmpty()) {
			redirect.addFlashAttribute("message", "password cannot be blank");
			return "redirect:/registerUser";
		}
		

		 MyUser u=repo.findByEmail(myuser.getEmail());

			if(u==null) {
				System.out.println("this user is null values");
				myuser.setPassword(bp.encode(myuser.getPassword().trim()));
				myuser.setRole("ROLE_USER");
				System.out.println(myuser);
				repo.save(myuser);
			}else {
				 redirect.addFlashAttribute("message", "User already Exist Choose Another");
				 
					return "redirect:/registerUser";	
			}
			
		return "/users/login";
	}
	
	
	
	@RequestMapping("/forgot-password")
	public ModelAndView forgetPassword(ModelAndView modelAndView) {
		modelAndView.setViewName("users/forget-password");
		return modelAndView;
	}
	
	@PostMapping("/send-otp")
	public ModelAndView sendOTP(ModelAndView modelAndView) {
		modelAndView.setViewName("users/forget-password");
		return modelAndView;
	}
	
	
	
}
