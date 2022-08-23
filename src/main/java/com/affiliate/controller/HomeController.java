package com.affiliate.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.bean.User;
import com.affiliate.repository.UserRepository;

//@Controller
@RestController
@RequestMapping("/user")
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder bp;

	@Autowired
	private UserRepository repo;

	@GetMapping("/home")
	public ModelAndView home(ModelAndView modelAndView, Principal principal) throws NumberFormatException {

		modelAndView.setViewName("users/business-panel-dashboard1");
		User u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		// System.out.println("user id: "+u.getUserid());

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
		User u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/notification-details");
		return modelAndView;
	}

	@RequestMapping("/notification")
	public ModelAndView notification(ModelAndView modelAndView, Principal principal) {
		User u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/notification");
		return modelAndView;
	}

	@RequestMapping("/payment")
	public ModelAndView payment(ModelAndView modelAndView, Principal principal) {
		User u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/payment");
		return modelAndView;
	}

	@RequestMapping("/privacy-policy")
	public ModelAndView privacyPolicy(ModelAndView modelAndView) {
		modelAndView.setViewName("users/privacy-policy");
		return modelAndView;
	}

	@RequestMapping("/profile-details")
	public ModelAndView profileDetails(ModelAndView modelAndView, Principal principal) throws Exception {
		System.out.println("this is profile");
		User u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/profile-details");

		return modelAndView;
	}

	@PostMapping("/updateUser")
	public ModelAndView showEditStudentPage(User user, ModelAndView modelAndView) {

		try {
			User u = repo.findByEmail(user.getEmail());

			if (u.getUserid() == null) {
				throw new UsernameNotFoundException("user not found");
			} else {

				repo.update(user.getFirstname(), user.getLastname(), user.getMobile(), user.getGender(),
						user.getAddress(), user.getCountry(), user.getState(), user.getCity(), user.getZip(),
						user.getEmail());
				System.out.println("updated record successfully....");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		modelAndView.setViewName("/login");

		return modelAndView;
	}
	
	
	@PostMapping("")
	public ModelAndView changePassword(ModelAndView modelAndView, HttpServletRequest request) {
		modelAndView.setViewName("");
		String password=request.getParameter("password");
		String newPassword=request.getParameter("newPassword");
		
		
		return modelAndView;
		
	}
	
	
	

	@RequestMapping("/sales-by-affiliate")
	public ModelAndView salesByAffiliate(ModelAndView modelAndView) {

		modelAndView.setViewName("users/sales_by_affiliate");
		return modelAndView;
	}

	@RequestMapping("/settings")
	public ModelAndView settings(Principal principal, ModelAndView modelAndView) {
		User u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/mysetting");
		return modelAndView;
	}

	@RequestMapping("/term-condition")
	public String termsAndConditions(Model model) {

		return "users/term-condition";
	}

	
	
	@RequestMapping("/myaffiliate")
	public String myAffiliate(Model model, Principal principal) {
		User u = repo.findByEmail(principal.getName());
		model.addAttribute("user_details", u);
		return "users/my-affiliate";
	}

	
	
	public String myUsername(Principal principal) {
		User u = repo.findByEmail(principal.getName());
		String name = u.getFirstname();
		return name;
	}

}
