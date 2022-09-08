package com.affiliate.user.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;
import com.affiliate.user.service.EmailService;
import com.affiliate.user.serviceimpl.EmailSenderServiceimpl;

@Controller
public class UserController {

	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	public EmailService emailService;

	@Autowired 
	private BCryptPasswordEncoder bp;
	
	Random random = new Random(1000);



	/*
	 * @PostMapping("/dologin") public ModelAndView loginVerify(ModelAndView
	 * modelAndView, @RequestParam("email") String email,
	 * 
	 * @RequestParam("password") String password) throws Exception {
	 * 
	 * Customer currentUser = this.repo.findByEmail(email); if (currentUser != null)
	 * { currentUser.getPassword(); if
	 * (this.myBcryptPasswordEncoder.password1().matches(password,
	 * currentUser.getPassword())) { modelAndView.addObject("fullname",
	 * currentUser.getFirstname() + " " + currentUser.getLastname());
	 * modelAndView.addObject("user_details", currentUser);
	 * modelAndView.setViewName("users/business-panel-dashboard1"); return
	 * modelAndView; } else { modelAndView.setViewName("users/login"); return
	 * modelAndView; }
	 * 
	 * } else { modelAndView.setViewName("users/login"); return modelAndView; } }
	 */

	@RequestMapping("/registerUser")
	public String registration() {

		return "users/registration-form";

	}

	@PostMapping("/addNewUser")
	public String registerUser(@ModelAttribute Customer myuser, RedirectAttributes redirect, Model model)
			throws Exception {
		if (myuser.getFirstname() == null || myuser.getFirstname().isEmpty()) {
			redirect.addFlashAttribute("message", "firstname cannot be blank");
			return "redirect:/registerUser";
		}
		if (myuser.getLastname() == null || myuser.getLastname().isEmpty()) {
			redirect.addFlashAttribute("message", "Last Name cannot be blank");
			return "redirect:/registerUser";
		}
		if (myuser.getEmail() == null || myuser.getEmail().isEmpty()) {
			redirect.addFlashAttribute("message", "email cannot be blank");
			return "redirect:/registerUser";
		}
		if (myuser.getPassword() == null || myuser.getPassword().isEmpty()) {
			redirect.addFlashAttribute("message", "password cannot be blank");
			return "redirect:/registerUser";
		}

		Customer u = repo.findByEmail(myuser.getEmail());

		if (u == null) {
			System.out.println("this user is null values");
			myuser.setPassword(bp.encode(myuser.getPassword().trim()));
			System.out.println(myuser);
			repo.save(myuser);
		} else {
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
	public ModelAndView sendOTP(ModelAndView modelAndView, @RequestParam("email") String to, HttpSession session) {
		// int generatedOtp = random.nextInt(9999);

		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSSS");
			LocalDateTime now = LocalDateTime.now();
			String time = dtf.format(now);
			String otp = time.split("\\.")[1];

			System.out.println("formatted date: " + otp);

			String from = "mobappssolutions146@gmail.com";
			String subject = "OTP from WebnMob Solutions";

			String messageBody = "Your One time password is" + otp
					+ " use this to reset your password this will expire in 5 minutes";

			Customer user = repo.findByEmail(to);

			if (user != null) {
				boolean flag = this.emailService.sendEmail(from, to, subject, messageBody);

				if (flag) {
					session.setAttribute("email", to);
					session.setAttribute("otp", otp);
					System.out.println("otp sent successfully...");
					session.setAttribute("message", "otp sent successfully");

					modelAndView.setViewName("users/verify-otp");
					return modelAndView;
				} else {

					session.setAttribute("message", "Please resend otp");
					modelAndView.setViewName("users/forget-password");
					return modelAndView;
				}
			} else {
				System.out.println(to + " not found");
				session.setAttribute("message", "Please provide correct email");
				modelAndView.setViewName("users/forget-password");
				return modelAndView;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@PostMapping("/verify-otp")
	public ModelAndView verifyOtp(@RequestParam("otp") String otp, HttpSession session, ModelAndView modelAndView) {
		try {
			String email = (String) session.getAttribute("email");
			String oldOtp = (String) session.getAttribute("otp");
			System.out.println("old otp is " + oldOtp);
			System.out.println("new otp is " + otp);
			if (oldOtp.equals(otp)) {
				session.setAttribute("message", "otp successfully verified.");
				modelAndView.setViewName("users/reset-password");
				return modelAndView;
			} else {
				session.setAttribute("message", "You have entered wrong otp");
				modelAndView.setViewName("users/forget-password");
				return modelAndView;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@PostMapping("/reset-password")
	public ModelAndView resetPassword(@RequestParam("password") String password, HttpSession session,
			ModelAndView modelAndView) {
		try {
			String email = (String) session.getAttribute("email");
			Customer currentUser = repo.findByEmail(email);
			if (currentUser != null) {
				System.out.println("user not null email is : " + email);
				currentUser.setPassword(bp.encode(password));
				repo.save(currentUser);
				modelAndView.setViewName("users/login");
				return modelAndView;
			} else {
				modelAndView.setViewName("users/forget-password");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return modelAndView;
	}

}
