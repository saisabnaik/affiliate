package com.affiliate.vender.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
import com.affiliate.vender.service.VenderUpdateService;

@RestController
@RequestMapping("/vender")
public class VenderController {

	@Autowired
	public VenderUpdateService venderUpdateService;
	
	private CustomerRepository customerRepository;
	
	@Autowired
	private VenderRepository venderRepo;
	
	@Autowired
	private CategoryRepository catRepository;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {
		System.out.println("vender login");
		modelAndView.setViewName("vender/login");
		
		return modelAndView;
	}
	
	
	@GetMapping("/home")
	public ModelAndView home(ModelAndView modelAndView, Principal principal) throws NumberFormatException, Exception {
		System.out.println("this is home page");
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		if (currentVender != null) {
			modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
			//modelAndView.addObject("vender_details", currentVender);

			modelAndView.setViewName("vender/dashboard");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}

	}
	

	@GetMapping("/profile-details")
	public ModelAndView profileDetails(ModelAndView modelAndView, Principal principal) throws Exception {
		
		System.out.println("profile");
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		if (currentVender != null) {

			modelAndView.addObject("vender_details", currentVender);
			modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
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
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		if (currentVender != null) {
			modelAndView.addObject("vender_details", currentVender);
			modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
			modelAndView.setViewName("vender/mysetting");
			return modelAndView;
		} else {
			modelAndView.setViewName("vender/login");
			return modelAndView;
		}

	}
	
	
	@PostMapping("/updateVender")
	public ModelAndView showEditStudentPage(Vender Vender, ModelAndView modelAndView, Principal principal,
			@RequestParam("userImage") MultipartFile profileImage) throws Exception {

		Vender currentVender = this.venderUpdateService.updateProfile(Vender, principal, profileImage);
		modelAndView.addObject("vender_details", currentVender);
		modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
		modelAndView.addObject("success", "Record updated successfully");
		modelAndView.setViewName("vender/mysetting");

		return modelAndView;
	}

	@PostMapping("/changePassword")
	public ModelAndView changePassword(ModelAndView modelAndView, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password, Principal principal) throws Exception {

		Vender currentVender = this.venderUpdateService.changeMyPassword(oldPassword, password, principal);
		modelAndView.addObject("vender_details", currentVender);
		modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
		modelAndView.addObject("success", "Your password hasbeen changed successfully...");
		modelAndView.setViewName("vender/mysetting");

		return modelAndView;
	}

	
	@GetMapping("/manage-business")
	public ModelAndView manageBusiness(ModelAndView modelAndView, Principal principal) throws Exception{
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		if (currentVender != null) {
			modelAndView.addObject("vender_details", currentVender);
			modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
			
			List<Customer> customers = this.venderUpdateService.getAllCustomer();
			
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
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
		modelAndView.setViewName("vender/notification");
		return modelAndView;
	}
	@GetMapping("/termsAndConditions")
	public ModelAndView termCondition(ModelAndView modelAndView, Principal principal) {
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
		modelAndView.setViewName("vender/term-condition");
		return modelAndView;
	}	
	
	@GetMapping("/contactus")
	public ModelAndView contactUs(ModelAndView modelAndView, Principal principal) {
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
		modelAndView.setViewName("vender/contact-us");
		return modelAndView;
	}	
	
	
	@GetMapping("/privacyPolicy")
	public ModelAndView privacyPolicy(ModelAndView modelAndView, Principal principal) {
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
		modelAndView.setViewName("vender/privacy-policy");
		return modelAndView;
	}
	
	@GetMapping("/add-product")
	public ModelAndView addProduct(ModelAndView modelAndView, Principal principal) {
		
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		modelAndView.addObject("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
		List<Category> productCategories = catRepository.findAll();
		modelAndView.addObject("category", productCategories);
		modelAndView.setViewName("vender/add-product");
		return modelAndView;
	}
	
	@PostMapping("/add-newProduct")
	public String addNewproduct(ModelAndView modelAndView, Principal principal,Product product,HttpSession session, @RequestParam("image") MultipartFile multipartFile,@RequestParam("pName") String pName,@RequestParam("pDesc") String pDesc,@RequestParam("pPrice") Integer pPrice,@RequestParam("pDiscount") Integer pDiscount,@RequestParam("pQuantity") Integer pQuantity,@RequestParam("pCategory") String category) {	
		System.out.println(product.getpName());
		boolean productresult = productService.addProduct(product,multipartFile);
		
		if(productresult) {
			session.setAttribute("p-added", "product added successfully..");
		}else {
			session.setAttribute("error", "Product not added please try again..");
		}
		
		//modelAndView.setViewName("vender/add-product");
		
		return "redirect: /vender/add-product";
		
	}

	
	
	

}
