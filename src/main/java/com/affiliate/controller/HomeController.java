package com.affiliate.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.affiliate.bean.MyUser;
import com.affiliate.repository.UserRepository;

//@Controller
@RestController
@RequestMapping("/user")
public class HomeController {

	@Autowired
	private BCryptPasswordEncoder bp;

	@Autowired
	private UserRepository repo;

	MyUser u=null;
	
	
	@GetMapping("/home")
	public ModelAndView home(ModelAndView modelAndView, Principal principal) throws NumberFormatException,Exception {

		modelAndView.setViewName("users/business-panel-dashboard1");
		u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		// System.out.println("user id: "+u.getMyUserid());

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
	public ModelAndView notificationDetails(ModelAndView modelAndView, Principal principal) throws Exception {
		//MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/notification-details");
		return modelAndView;
	}

	@RequestMapping("/notification")
	public ModelAndView notification(ModelAndView modelAndView, Principal principal) throws Exception  {
		//MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/notification");
		return modelAndView;
	}

	@RequestMapping("/payment")
	public ModelAndView payment(ModelAndView modelAndView, Principal principal) throws Exception {
		//MyUser u = repo.findByEmail(principal.getName());
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
		//MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/profile-details");

		return modelAndView;
	}

	@PostMapping("/updateMyUser")
	public ModelAndView showEditStudentPage(MyUser myuser, ModelAndView modelAndView, RedirectAttributes redirectAttributes) throws Exception{

		try {
			//MyUser u = repo.findByEmail(myuser.getEmail());
			modelAndView.addObject("user_details", u);
			if (u.getUserid() == null) {
				throw new UsernameNotFoundException("user not found");
			} else {

				repo.updateData(myuser.getFirstname(), myuser.getLastname(), myuser.getMobile(), myuser.getGender(),
						myuser.getAddress(), myuser.getCountry(), myuser.getState(), myuser.getCity(), myuser.getZip(),
						myuser.getEmail());
				
				redirectAttributes.addFlashAttribute("successMsg");
				
				System.out.println("updated record successfully....");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		modelAndView.setViewName("users/mysetting");

		return modelAndView;
	}
	
	
	@PostMapping("/changePassword")
	public ModelAndView changePassword(ModelAndView modelAndView, HttpServletRequest request,Principal principal,RedirectAttributes redirect) throws Exception{
		System.out.println("this is change password method called");
		//MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		String oldPwd=bp.encode(request.getParameter("oldPassword").trim());
		String newPassword =bp.encode(request.getParameter("password").trim());

		String dbUserPassword = u.getPassword();
		System.out.println("oldPwd"+oldPwd);
		System.out.println("db password "+dbUserPassword);
		
		
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		
		//if(dbUserPassword.equals(oldPwd)) {
			//System.out.println("if condition");
				//repo.updatePassword(newPassword,u.getEmail());
				//System.out.println("record updated successfully");
	//	}
		
		
		//if() {
			
		//}else {
		//	redirect.addFlashAttribute("messageP", "you have entered wrong password");
		//}
		modelAndView.setViewName("users/mysetting");
		
		return modelAndView;
		
	}
	
	
	

	@RequestMapping("/sales-by-affiliate")
	public ModelAndView salesByAffiliate(ModelAndView modelAndView, Principal principal) throws Exception {
		//MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/sales_by_affiliate");
		return modelAndView;
	}

	@RequestMapping("/settings")
	public ModelAndView settings(Principal principal, ModelAndView modelAndView) throws Exception {
		//MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/mysetting");
		return modelAndView;
	}

	@RequestMapping("/term-condition")
	public String termsAndConditions(Model model) {

		return "users/term-condition";
	}

	
	
	@RequestMapping("/myaffiliate")
	public ModelAndView myAffiliate(ModelAndView modelAndView, Principal principal) throws Exception {
		//MyUser u = repo.findByEmail(principal.getName());
		modelAndView.addObject("user_details", u);
		modelAndView.setViewName("users/my-affiliate");
		return modelAndView;
	}

	
	
	public String myMyUsername(Principal principal) throws Exception {
		//MyUser u = repo.findByEmail(principal.getName());
		String name = u.getFirstname();
		return name;
	}

}
