package com.affiliate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.affiliate.bean.User;
import com.affiliate.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder bp;
	
	@RequestMapping("/login")
	public String login() {
		
		return "users/login";
	}
	
	@RequestMapping("/registerUser")
	public String registration() {
		
		return "users/registration-form";
		
	}

	
	@PostMapping("/addNewUser")
	public String registerUser(@ModelAttribute User user,  RedirectAttributes redirect,Model model) {
		
		if(user.getFirstname()==null || user.getFirstname().isEmpty()) {
			redirect.addFlashAttribute("message", "firstname cannot be blank");
			return "redirect:/registerUser";
		}
		if(user.getLastname()==null || user.getLastname().isEmpty()) {
			redirect.addFlashAttribute("message", "Last Name cannot be blank");
			return "redirect:/registerUser";
		}
		if(user.getEmail()==null || user.getEmail().isEmpty()) {
			redirect.addFlashAttribute("message", "email cannot be blank");
			return "redirect:/registerUser";
		}
		if(user.getPassword()==null || user.getPassword().isEmpty()) {
			redirect.addFlashAttribute("message", "password cannot be blank");
			return "redirect:/registerUser";
		}
		user.setPassword(bp.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		System.out.println(user);
		repo.save(user);
		//model.addAttribute("success", "registration successfully");
		return "/users/login";
	}
	
	
}
