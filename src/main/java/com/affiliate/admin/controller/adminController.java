package com.affiliate.admin.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.admin.AdminRepository;
import com.affiliate.admin.MyAdmin;
import com.affiliate.admin.service.AdminUpdateService;
import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;

@RestController
@RequestMapping("/admin")
public class adminController {
	//@Autowired
	//private UpdateAdminServiceImpl adminUpdateProfileImpl;
	@Autowired
	public AdminUpdateService adminUpdateService;
	
	private CustomerRepository customerRepository;
	
	@Autowired
	private AdminRepository amdinRepo;
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {
		System.out.println("admin login");
		modelAndView.setViewName("super-admin/login");
		
		return modelAndView;
	}
	
	
	@GetMapping("/home")
	public ModelAndView home(ModelAndView modelAndView, Principal principal) throws NumberFormatException, Exception {
		System.out.println("this is home page");
		MyAdmin currentAdmin = this.amdinRepo.findByEmail(principal.getName());
		if (currentAdmin != null) {
			modelAndView.addObject("fullname", currentAdmin.getFirstname() + " " + currentAdmin.getLastname());
			//modelAndView.addObject("admin_details", currentAdmin);

			modelAndView.setViewName("super-admin/dashboard");
			return modelAndView;
		} else {
			modelAndView.setViewName("super-admin/login");
			return modelAndView;
		}

	}
	

	@GetMapping("/profile-details")
	public ModelAndView profileDetails(ModelAndView modelAndView, Principal principal) throws Exception {
		
		System.out.println("profile");
		MyAdmin currentAdmin = this.amdinRepo.findByEmail(principal.getName());
		if (currentAdmin != null) {

			modelAndView.addObject("admin_details", currentAdmin);
			modelAndView.addObject("fullname", currentAdmin.getFirstname() + " " + currentAdmin.getLastname());
			modelAndView.setViewName("super-admin/profile-details");
			return modelAndView;
		} else {
			modelAndView.setViewName("super-admin/login");
			return modelAndView;
		}

	}
	
	@RequestMapping("/settings")
	public ModelAndView settings(Principal principal, ModelAndView modelAndView) throws Exception {
		System.out.println("settings");
		MyAdmin currentAdmin = this.amdinRepo.findByEmail(principal.getName());
		if (currentAdmin != null) {
			modelAndView.addObject("admin_details", currentAdmin);
			modelAndView.addObject("fullname", currentAdmin.getFirstname() + " " + currentAdmin.getLastname());
			modelAndView.setViewName("super-admin/mysetting");
			return modelAndView;
		} else {
			modelAndView.setViewName("super-admin/login");
			return modelAndView;
		}

	}
	
	
	@PostMapping("/updateMyAdmin")
	public ModelAndView showEditStudentPage(MyAdmin myAdmin, ModelAndView modelAndView, Principal principal,
			@RequestParam("userImage") MultipartFile profileImage) throws Exception {

		MyAdmin currentAdmin = this.adminUpdateService.updateProfile(myAdmin, principal, profileImage);
		modelAndView.addObject("admin_details", currentAdmin);
		modelAndView.addObject("fullname", currentAdmin.getFirstname() + " " + currentAdmin.getLastname());
		modelAndView.addObject("success", "Record updated successfully");
		modelAndView.setViewName("super-admin/mysetting");

		return modelAndView;
	}

	@PostMapping("/changePassword")
	public ModelAndView changePassword(ModelAndView modelAndView, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password, Principal principal) throws Exception {

		MyAdmin currentAdmin = this.adminUpdateService.changeMyPassword(oldPassword, password, principal);
		modelAndView.addObject("admin_details", currentAdmin);
		modelAndView.addObject("fullname", currentAdmin.getFirstname() + " " + currentAdmin.getLastname());
		modelAndView.addObject("success", "Your password hasbeen changed successfully...");
		modelAndView.setViewName("super-admin/mysetting");

		return modelAndView;
	}

	
	@GetMapping("/manage-business")
	public ModelAndView manageBusiness(ModelAndView modelAndView, Principal principal) throws Exception{
		MyAdmin currentAdmin = this.amdinRepo.findByEmail(principal.getName());
		if (currentAdmin != null) {
			modelAndView.addObject("admin_details", currentAdmin);
			modelAndView.addObject("fullname", currentAdmin.getFirstname() + " " + currentAdmin.getLastname());
			
			List<Customer> customers = this.adminUpdateService.getAllCustomer();
			
			if (customers.isEmpty() == false) {
				modelAndView.addObject("customers", customers);
				modelAndView.setViewName("super-admin/manage-business");
				return modelAndView;
			} else {
				modelAndView.setViewName("super-admin/manage-business");
				modelAndView.addObject("noDataMsg", "No Record found");
				return modelAndView;
			}

		} else {
			modelAndView.setViewName("super-admin/login");
			return modelAndView;
		}
	}
	
	
	
	

}
