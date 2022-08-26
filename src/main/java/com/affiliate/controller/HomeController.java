package com.affiliate.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.affiliate.bean.MyAffiliate;
import com.affiliate.bean.MyUser;
import com.affiliate.repository.UserAffiliateRepository;
import com.affiliate.repository.UserRepository;
import com.affiliate.user.service.UserUpdateAndChangePassword;


@RestController
@RequestMapping("/user")
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository repo;

	@Autowired
	private UserAffiliateRepository userAffiliateRepository;
	
	@Autowired
	private UserUpdateAndChangePassword userUpdateAndChangePassword;
	
	
	
	@GetMapping("/home")
	public ModelAndView home(ModelAndView modelAndView, Principal principal) throws NumberFormatException,Exception {

		MyUser currentUser = this.repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", currentUser);

		modelAndView.setViewName("users/business-panel-dashboard1");
		return modelAndView;
	}


	@RequestMapping("/profile-details")
	public ModelAndView profileDetails(ModelAndView modelAndView, Principal principal) throws Exception {
		System.out.println("this is profile");
		MyUser currentUser = this.repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", currentUser);
		modelAndView.setViewName("users/profile-details");

		return modelAndView;
	}

	@PostMapping("/updateMyUser")
	public ModelAndView showEditStudentPage(MyUser myuser, ModelAndView modelAndView,Principal principal) throws Exception{

		MyUser currentUser =this.userUpdateAndChangePassword.updatePassword(myuser, principal);
				modelAndView.addObject("user_details", currentUser);
				modelAndView.addObject("success", "Record updated successfully");
				modelAndView.setViewName("users/mysetting");

		return modelAndView;
	}
	
	
	@PostMapping("/changePassword")
	public ModelAndView changePassword(ModelAndView modelAndView, @RequestParam("oldPassword") String oldPassword,@RequestParam("password") String password ,Principal principal) throws Exception{

		MyUser currentUser = this.userUpdateAndChangePassword.changeMyPassword(oldPassword, password, principal );
		modelAndView.addObject("user_details", currentUser);
		modelAndView.addObject("success","Your password hasbeen changed successfully...");
		modelAndView.setViewName("users/mysetting");
		
		return modelAndView;
		
	}

	@RequestMapping("/settings")
	public ModelAndView settings(Principal principal, ModelAndView modelAndView) throws Exception {
		MyUser currentUser = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", currentUser);
		modelAndView.setViewName("users/mysetting");
		return modelAndView;
	}

	@RequestMapping("/myaffiliate")
	public ModelAndView myAffiliate(ModelAndView modelAndView, Principal principal) throws Exception {
		MyUser currentUser = this.repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", currentUser);
		
	
		
		modelAndView.setViewName("users/my-affiliate");
		return modelAndView;
	}

	
	
	
	
	
	/* *****************************************************  */

	/*


	
	@RequestMapping("/sales-by-affiliate")
	public ModelAndView salesByAffiliate(ModelAndView modelAndView, Principal principal) throws Exception {
		MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/sales_by_affiliate");
		return modelAndView;
	}





	@RequestMapping("/contact-us")
	public String contactUs() {

		return "users/contact-us";
	}



	@RequestMapping("/notification-details")
	public ModelAndView notificationDetails(ModelAndView modelAndView, Principal principal) throws Exception {
		MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/notification-details");
		return modelAndView;
	}

	@RequestMapping("/notification")
	public ModelAndView notification(ModelAndView modelAndView, Principal principal) throws Exception  {
		MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/notification");
		return modelAndView;
	}

	@RequestMapping("/payment")
	public ModelAndView payment(ModelAndView modelAndView, Principal principal) throws Exception {
		MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/payment");
		return modelAndView;
	}

	@RequestMapping("/privacy-policy")
	public ModelAndView privacyPolicy(ModelAndView modelAndView) {
		modelAndView.setViewName("users/privacy-policy");
		return modelAndView;
	}
	
	
	
	@RequestMapping("/term-condition")
	public String termsAndConditions(Model model) {

		return "users/term-condition";
	}

	
	

*/

}
