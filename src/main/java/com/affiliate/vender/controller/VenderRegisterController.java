package com.affiliate.vender.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.customer.service.EmailService;
import com.affiliate.vender.Vender;
import com.affiliate.vender.VenderRepository;

@Controller
public class VenderRegisterController {

	@Autowired
	private VenderRepository venderRepo;

	@Autowired
	private BCryptPasswordEncoder bp;
	@Autowired
	private EmailService emailService;
	

	@RequestMapping("/v1/Register")
	public ModelAndView venderRegistration(ModelAndView modelAndView) {

		modelAndView.setViewName("vender/registration-form");
		return modelAndView;
	}

	@PostMapping("/v1/addVender")
	public String registerUser(@ModelAttribute Vender vender, HttpSession session) throws Exception {

		
		if (vender.getFirstname() == null || vender.getFirstname().isEmpty()) {

			session.setAttribute("error_message", "firstname cannot be blank");
			return "redirect:/v1/registerUser";
		} else {
			if (vender.getLastname() == null || vender.getLastname().isEmpty()) {
				session.setAttribute("error_message", "Last Name cannot be blank");
				// redirect.addFlashAttribute("message", "Last Name cannot be blank");
				
			} else {
				if (vender.getEmail() == null || vender.getEmail().isEmpty()) {
					session.setAttribute("error_message", "email cannot be blank");
					
				} else {
					if (vender.getPassword() == null || vender.getPassword().isEmpty()) {
						session.setAttribute("error_message", "password cannot be blank");
						
					}else {
						Vender vr = venderRepo.findByEmail(vender.getEmail());

						if (vr == null) {
							vender.setPassword(bp.encode(vender.getPassword().trim()));
							System.out.println(vender);
							venderRepo.save(vender);
							System.out.println("vender registered successfully..");
							session.setAttribute("succussRegister", "You have successfully registered..");
							
						}else {
							session.setAttribute("error_message", "User already exist.");
						}
					}
				}
			}
		}

		return "redirect:/v1/Register";
	}


}
