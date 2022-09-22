package com.affiliate.vender.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;
import com.affiliate.product.Category;
import com.affiliate.product.Product;
import com.affiliate.product.repository.CategoryRepository;
import com.affiliate.product.service.ProductService;
import com.affiliate.vender.Vender;
import com.affiliate.vender.VenderRepository;
import com.affiliate.vender.service.VenderService;


@RestController
@RequestMapping("/vender")
public class VenderController {

	@Autowired
	public VenderService venderService;

	private CustomerRepository customerRepository;

	@Autowired
	private VenderRepository venderRepo;

	@Autowired
	private CategoryRepository catRepository;

	@Autowired
	private ProductService productService;

	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {
		modelAndView.setViewName("vender/login");
		return modelAndView;
	}
	
	
	
	

	@GetMapping("/home")
	public ModelAndView home(ModelAndView modelAndView, Principal principal, HttpSession session)
			throws NumberFormatException, Exception {
		if (principal.getName() != null) {
			Vender currentVender = this.venderRepo.findByEmail(principal.getName());
			session.setAttribute("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
			modelAndView.setViewName("vender/dashboard");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}

	}

	@GetMapping("/profile-details")
	public ModelAndView profileDetails(ModelAndView modelAndView, Principal principal) throws Exception {

		if (principal.getName() != null) {
			Vender currentVender = this.venderRepo.findByEmail(principal.getName());
			modelAndView.addObject("vender_details", currentVender);
			modelAndView.setViewName("vender/profile-details");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}

	}

	@RequestMapping("/settings")
	public ModelAndView settings(Principal principal, ModelAndView modelAndView) throws Exception {
		System.out.println("settings");
		if (principal.getName() != null) {
			Vender currentVender = this.venderRepo.findByEmail(principal.getName());
			modelAndView.addObject("vender_details", currentVender);
			modelAndView.setViewName("vender/mysetting");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}

	}

	@PostMapping("/updateVender")
	public ModelAndView showEditStudentPage(Vender Vender, ModelAndView modelAndView, Principal principal,
			@RequestParam("userImage") MultipartFile profileImage, HttpSession session) throws Exception {
		if (principal.getName() != null) {
			Vender currentVender = this.venderService.updateProfile(Vender, principal, profileImage);
			modelAndView.addObject("vender_details", currentVender);
			session.setAttribute("success", "Record updated successfully");
			modelAndView.setViewName("vender/mysetting");

			return modelAndView;

		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}
	}

	@PostMapping("/changePassword")
	public ModelAndView changePassword(ModelAndView modelAndView, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password, Principal principal) throws Exception {
		if (principal.getName() != null) {
			Vender currentVender = this.venderService.changeMyPassword(oldPassword, password, principal);
			modelAndView.addObject("vender_details", currentVender);
			modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
			modelAndView.addObject("success", "Your password hasbeen changed successfully...");
			modelAndView.setViewName("vender/mysetting");
			return modelAndView;

		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}
	}

	@GetMapping("/manage-business")
	public ModelAndView manageBusiness(ModelAndView modelAndView, Principal principal) throws Exception {
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		if (principal.getName() != null) {
			modelAndView.addObject("vender_details", currentVender);

			List<Customer> customers = this.venderService.getAllCustomer();

			if (customers.isEmpty() == false) {
				modelAndView.addObject("customers", customers);
				modelAndView.setViewName("vender/manage-business");
				return modelAndView;
			} else {
				modelAndView.setViewName("vender/manage-business");
				modelAndView.addObject("noDataMsg", "No Record found");

				return modelAndView;
			}

		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}
	}

	@GetMapping("/notification")
	public ModelAndView notification(ModelAndView modelAndView, Principal principal) {
		if (principal.getName() != null) {
			modelAndView.setViewName("vender/notification");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}
	}

	@GetMapping("/termsAndConditions")
	public ModelAndView termCondition(ModelAndView modelAndView, Principal principal) {

		if (principal.getName() != null) {
			modelAndView.setViewName("vender/term-condition");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}
	}

	@GetMapping("/contactus")
	public ModelAndView contactUs(ModelAndView modelAndView, Principal principal) {
		if (principal.getName() != null) {
			modelAndView.setViewName("vender/contact-us");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}

	}

	@GetMapping("/privacyPolicy")
	public ModelAndView privacyPolicy(ModelAndView modelAndView, Principal principal) {
		if (principal.getName() != null) {
			modelAndView.setViewName("vender/privacy-policy");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}
	}

	@GetMapping("/add-product")
	public ModelAndView addProduct(ModelAndView modelAndView, Principal principal) {
		if (principal.getName() != null) {
			List<Category> productCategories = catRepository.findAll();

			modelAndView.addObject("category", productCategories);
			modelAndView.setViewName("product/add-product");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}

	}

	@PostMapping("/add-newProduct")
	public ModelAndView addNewproduct(ModelAndView modelAndView, Principal principal, Product product, @RequestParam("image") MultipartFile 		multipartFile , @RequestParam("category") Category category , HttpSession session) {

		if (principal.getName() != null) {
			Vender currentVender = this.venderRepo.findByEmail(principal.getName());
			int venderid = currentVender.getVenderId();
			boolean productresult=productService.addProduct(product,category,multipartFile, currentVender.getVenderId());
			if(productresult) {
				System.out.println("product added successfully");
			}else {
				System.out.println("product not added please try again");
			}	
			
			modelAndView.setViewName("product/add-product");
			return modelAndView;

		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}
		
		
		


	}

}
