package com.affiliate.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String registerUser(@ModelAttribute User user, HttpSession session) {
		user.setPassword(bp.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		System.out.println(user);
		User savedUser=(User)repo.save(user);
		System.out.println(savedUser);
		session.setAttribute("success", "registration successfully");
		return "/users/login";
	}
	
	
}
