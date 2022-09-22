package com.affiliate.vender.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.customer.service.EmailService;
import com.affiliate.vender.Vender;
import com.affiliate.vender.VenderRepository;

@Controller
public class VenderForgotPassword {

	@Autowired
	private VenderRepository venderRepo;

	@Autowired
	private BCryptPasswordEncoder bp;
	@Autowired
	private EmailService emailService;
	
	
	
	@RequestMapping("/v1/forgot-password")
	public ModelAndView forgetPassword(ModelAndView modelAndView) {
		modelAndView.setViewName("vender/forget-password");
		return modelAndView;
	}

	@PostMapping("/v1/send-otp")
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

			Vender vender = venderRepo.findByEmail(to);

			if (vender != null) {
				boolean flag = this.emailService.sendEmail(from, to, subject, messageBody);

				if (flag) {
					session.setAttribute("email", to);
					session.setAttribute("otp", otp);
					System.out.println("otp sent successfully...");
					session.setAttribute("success", "otp sent successfully");

					modelAndView.setViewName("vender/verify-otp");
					return modelAndView;
				} else {

					session.setAttribute("error", "Please resend otp");
					modelAndView.setViewName("vender/forget-password");
					return modelAndView;
				}
			} else {
				System.out.println(to + " not found");
				session.setAttribute("error", "Entered email doesnot exist.");
				modelAndView.setViewName("vender/forget-password");
				return modelAndView;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return modelAndView;
	}

	@PostMapping("/v1/verify-otp")
	public ModelAndView verifyOtp(@RequestParam("otp") String otp, HttpSession session, ModelAndView modelAndView) {
		try {
			String email = (String) session.getAttribute("email");
			String oldOtp = (String) session.getAttribute("otp");
			System.out.println("old otp is " + oldOtp);
			System.out.println("new otp is " + otp);
			if (oldOtp.equals(otp)) {
				System.out.println("verify otp called");
				session.setAttribute("success", "otp successfully verified.");
				modelAndView.setViewName("vender/reset-password");
				return modelAndView;
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}

		session.setAttribute("error!", "You have entered wrong otp");
		modelAndView.setViewName("vender/forget-password");
		return modelAndView;
	}
	
	@PostMapping("/v1/reset-password")
	public ModelAndView resetPassword(@RequestParam("password") String password, HttpSession session,
			ModelAndView modelAndView) {
		try {
			System.out.println("reset password method called");
			String email = (String) session.getAttribute("email");
			Vender currentVender = venderRepo.findByEmail(email);
			if (currentVender != null) {
				System.out.println("user not null email is : " + email);
				currentVender.setPassword(bp.encode(password));
				venderRepo.save(currentVender);
				session.setAttribute("success", "You have successfully reset password");
				modelAndView.setViewName("vender/login");
				return modelAndView;
			} else {
				modelAndView.setViewName("vender/forget-password");
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return modelAndView;
	}
	
	
	
	
}
