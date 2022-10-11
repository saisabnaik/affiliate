package com.affiliate.customer.controller;

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
import com.affiliate.customer.service.AffiliateIdGenerateService;
import com.affiliate.customer.service.EmailService;
import com.affiliate.customer.serviceimpl.EmailSenderServiceimpl;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository repo;

	@Autowired
	public EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder bp;

	// Random random = new Random(1000);
	@Autowired
	private AffiliateIdGenerateService affiliateIdGenerateService;

	@RequestMapping("/registerUser")
	public String registration() {

		return "users/registration-form";

	}

	@PostMapping("/addNewUser")
	public String registerUser(@ModelAttribute Customer myuser, HttpSession session, Model model) throws Exception {
		if (myuser.getFirstname() == null || myuser.getFirstname().isEmpty()) {
			session.setAttribute("error", "firstname cannot be blank");
			return "redirect:/registerUser";
		} else {
			if (myuser.getLastname() == null || myuser.getLastname().isEmpty()) {
				session.setAttribute("error", "Last Name cannot be blank");
				return "redirect:/registerUser";
			} else {
				if (myuser.getEmail() == null || myuser.getEmail().isEmpty()) {
					session.setAttribute("error", "email cannot be blank");
					return "redirect:/registerUser";
				} else {
					if (myuser.getPassword() == null || myuser.getPassword().isEmpty()) {
						session.setAttribute("error", "password cannot be blank");
						return "redirect:/registerUser";
					}
				}
			}

		}

		Customer u = repo.findByEmail(myuser.getEmail());

		if (u == null) {
			System.out.println("this user is null values");
			myuser.setPassword(bp.encode(myuser.getPassword().trim()));
			
			myuser.setAffiliateId(affiliateIdGenerateService.generateAffiliateId());
			
			repo.save(myuser);
			
		} else {
			session.setAttribute("error", "User already Exist Choose Another");
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
					session.setAttribute("success", "otp sent successfully");

					modelAndView.setViewName("users/verify-otp");
					return modelAndView;
				} else {

					session.setAttribute("success", "Please resend otp");
					modelAndView.setViewName("users/forget-password");
					return modelAndView;
				}
			} else {
				System.out.println(to + " not found");
				session.setAttribute("error", "Entered email does not exist.");
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
				session.setAttribute("success", "otp successfully verified.");
				modelAndView.setViewName("users/reset-password");
				return modelAndView;
			} else {
				session.setAttribute("error", "You have entered wrong otp");
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
