package com.affiliate.user.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.bean.CustomerMyAffiliate;
import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;
import com.affiliate.repository.CustomerAffiliateRepository;
import com.affiliate.user.service.UserUpdate;


@RestController
@RequestMapping("/customer")
public class HomeController {

	@Autowired
	public UserUpdate userUpdate;
	
	@Autowired
	private BCryptPasswordEncoder bp;

	@Autowired
	private CustomerRepository repo;

	//@Autowired
	//private UserUpdateAndChangePassword userUpdateAndChangePassword;

	@Autowired
	private CustomerAffiliateRepository userAffiliateRepository;


	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {
		System.out.println("login");
		modelAndView.setViewName("users/login");
		
		return modelAndView;
	}
	

	
	@GetMapping("/home")
	public ModelAndView home(ModelAndView modelAndView, Principal principal) throws NumberFormatException, Exception {

		Customer currentCustomer = this.repo.findByEmail(principal.getName());
		if (currentCustomer != null) {
			modelAndView.addObject("fullname", currentCustomer.getFirstname() + " " + currentCustomer.getLastname());
			modelAndView.addObject("user_details", currentCustomer);

			modelAndView.setViewName("users/dashboard");
			return modelAndView;
		} else {
			modelAndView.setViewName("users/login");
			return modelAndView;
		}

	}

	@RequestMapping("/profile-details")
	public ModelAndView profileDetails(ModelAndView modelAndView, Principal principal) throws Exception {
		System.out.println("profile");
		Customer currentCustomer = this.repo.findByEmail(principal.getName());
		if (currentCustomer != null) {

			modelAndView.addObject("user_details", currentCustomer);
			modelAndView.addObject("fullname", currentCustomer.getFirstname() + " " + currentCustomer.getLastname());
			modelAndView.setViewName("users/profile-details");
			return modelAndView;
		} else {
			modelAndView.setViewName("users/login");
			return modelAndView;
		}

	}

	@PostMapping("/updateMyUser")
	public ModelAndView showEditStudentPage(Customer myuser, ModelAndView modelAndView, Principal principal,
			@RequestParam("userImage") MultipartFile profileImage) throws Exception {

		Customer currentCustomer = this.userUpdate.updateProfile(myuser, principal, profileImage);
		modelAndView.addObject("user_details", currentCustomer);
		modelAndView.addObject("fullname", currentCustomer.getFirstname() + " " + currentCustomer.getLastname());
		modelAndView.addObject("success", "Record updated successfully");
		modelAndView.setViewName("users/mysetting");

		return modelAndView;
	}

	@PostMapping("/changePassword")
	public ModelAndView changePassword(ModelAndView modelAndView, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password, Principal principal) throws Exception {

		Customer currentCustomer = this.userUpdate.changeMyPassword(oldPassword, password, principal);
		modelAndView.addObject("user_details", currentCustomer);
		modelAndView.addObject("fullname", currentCustomer.getFirstname() + " " + currentCustomer.getLastname());
		modelAndView.addObject("success", "Your password hasbeen changed successfully...");
		modelAndView.setViewName("users/mysetting");

		return modelAndView;

	}

	@RequestMapping("/settings")
	public ModelAndView settings(Principal principal, ModelAndView modelAndView) throws Exception {
		Customer currentCustomer = repo.findByEmail(principal.getName());
		if (currentCustomer != null) {
			modelAndView.addObject("user_details", currentCustomer);
			modelAndView.addObject("fullname", currentCustomer.getFirstname() + " " + currentCustomer.getLastname());
			modelAndView.setViewName("users/mysetting");
			return modelAndView;
		} else {
			modelAndView.setViewName("users/login");
			return modelAndView;
		}

	}

	@RequestMapping("/myaffiliate")
	public ModelAndView myAffiliate(ModelAndView modelAndView, Principal principal) throws Exception {
		Customer currentCustomer = this.repo.findByEmail(principal.getName());
		if (currentCustomer != null) {
			modelAndView.addObject("fullname", currentCustomer.getFirstname() + " " + currentCustomer.getLastname());
			modelAndView.setViewName("users/my-affiliate");
			System.out.println("myaffiliate called....");

			 List<CustomerMyAffiliate> affiliatelist = currentCustomer.getAffiliateList();

			if (affiliatelist.isEmpty() == false) {
				modelAndView.addObject("affiliatelist", affiliatelist);
				return modelAndView;
			} else {
				modelAndView.addObject("noDataMsg", "No Record found");
				return modelAndView;
			}

		} else {
			modelAndView.setViewName("users/login");
			return modelAndView;
		}

	}

//total sale by affiliate
	@RequestMapping("/payment")
	public ModelAndView totalSale(ModelAndView modelAndView, Principal principal) throws Exception {
		Customer currentUser = this.repo.findByEmail(principal.getName());

		if (currentUser != null) {
			modelAndView.addObject("fullname", currentUser.getFirstname() + " " + currentUser.getLastname());
			modelAndView.setViewName("users/payment");
			System.out.println("myaffiliate called....");

			List<CustomerMyAffiliate> affiliatelist = currentUser.getAffiliateList();

			if (affiliatelist.isEmpty() == false) {
				modelAndView.addObject("affiliatelist", affiliatelist);

				return modelAndView;
			} else {
				modelAndView.addObject("noDataMsg", "No Record found");
				return modelAndView;
			}
		} else {
			modelAndView.setViewName("users/login");
			return modelAndView;
		}
	}
	
	@GetMapping("/notification")
	public ModelAndView notification(ModelAndView modelAndView, Principal principal) {
		Customer currentUser = this.repo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentUser.getFirstname() + " " + currentUser.getLastname());
		modelAndView.setViewName("users/notification");
		return modelAndView;
	}
	@GetMapping("/term-condition")
	public ModelAndView termCondition(ModelAndView modelAndView, Principal principal) {
		Customer currentUser = this.repo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentUser.getFirstname() + " " + currentUser.getLastname());
		modelAndView.setViewName("users/term-condition");
		return modelAndView;
	}	
	
	@GetMapping("/contact-us")
	public ModelAndView contactUs(ModelAndView modelAndView, Principal principal) {
		Customer currentUser = this.repo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentUser.getFirstname() + " " + currentUser.getLastname());
		modelAndView.setViewName("users/contact-us");
		return modelAndView;
	}	
	
	
	@GetMapping("/privacy-policy")
	public ModelAndView privacyPolicy(ModelAndView modelAndView, Principal principal) {
		Customer currentUser = this.repo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentUser.getFirstname() + " " + currentUser.getLastname());
		modelAndView.setViewName("users/privacy-policy");
		return modelAndView;
	}
	

}
