package com.affiliate.customer.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;
import com.affiliate.customer.service.UserUpdate;
import com.affiliate.model.MyAffiliate;
import com.affiliate.product.Product;
import com.affiliate.product.repository.CategoryRepository;
import com.affiliate.product.repository.ProductRepository;
import com.affiliate.product.service.ProductService;
import com.affiliate.repository.AffiliateRepository;
import com.affiliate.vender.Vender;

@Controller
@RequestMapping("/customer")
public class HomeController {

	@Autowired
	public UserUpdate userUpdate;

	@Autowired
	private BCryptPasswordEncoder bp;

	@Autowired
	private CustomerRepository repo;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private AffiliateRepository userAffiliateRepository;
	
	@Autowired
	private ProductService productService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/login")
	public String login(ModelAndView modelAndView) {
		return "users/login";
	}

	@GetMapping("/home")
	public String home(Model model, Principal principal, HttpSession session) throws NumberFormatException, Exception {

		if (principal.getName() != null) {
			Customer currentCustomer = this.repo.findByEmail(principal.getName());
			session.setAttribute("fullname", currentCustomer.getFirstname() + " " + currentCustomer.getLastname());
			model.addAttribute("user_details", currentCustomer);
			session.setAttribute("fullname", currentCustomer.getFirstname()+ " "+currentCustomer.getLastname());
			session.setAttribute("userid", currentCustomer.getUserid());
			model.addAttribute("categories", categoryRepository.findAll());
			session.setAttribute("myEmail", principal.getName());
			session.setAttribute("affiliatid", currentCustomer.getAffiliateId());
			return "redirect:/";
		} else {
			session.invalidate();
			return "redirect:/";
		}

	}

	@GetMapping("/dashboard")
	public String dashboad(Model model, Principal principal, HttpSession session)
			throws NumberFormatException, Exception {
		if (principal.getName() != null) {
			Customer currentCustomer = this.repo.findByEmail(principal.getName());
			//session.setAttribute("fullname", currentCustomer.getFirstname() + " " + currentCustomer.getLastname());
			model.addAttribute("user_details", currentCustomer);
			return "users/dashboard";

		} else {
			session.invalidate();
			return "redirect:/";
		}

	}

	@RequestMapping("/profile-details")
	public String profileDetails(Model model, Principal principal, HttpSession session) throws Exception {
		System.out.println("profile");
		Customer currentCustomer = this.repo.findByEmail(principal.getName());
		if (currentCustomer != null) {
			model.addAttribute("user_details", currentCustomer);

			return "users/profile-details";
		} else {
			session.invalidate();
			return "redirect:/";
		}

	}

	@PostMapping("/updateMyUser")
	public ModelAndView showEditStudentPage(Customer myuser, ModelAndView modelAndView, Principal principal,
			@RequestParam(value="userImage",required=false) MultipartFile profileImage,HttpServletRequest request) throws Exception {

		Customer currentCustomer = this.userUpdate.updateProfile(myuser, principal, profileImage,request);
		modelAndView.addObject("user_details", currentCustomer);
		modelAndView.addObject("success", "Record updated successfully");
		modelAndView.setViewName("users/mysetting");

		return modelAndView;
	}
	
	@GetMapping("/customer-icon/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response,HttpSession session)
			throws ServletException, IOException {
		Customer customer=repo.findByUserid(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(customer.getImage());
		response.getOutputStream().close();

	}

	@PostMapping("/changePassword")
	public ModelAndView changePassword(ModelAndView modelAndView, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password, Principal principal) throws Exception {

		Customer currentCustomer = this.userUpdate.changeMyPassword(oldPassword, password, principal);
		modelAndView.addObject("user_details", currentCustomer);
		modelAndView.addObject("success", "Your password hasbeen changed successfully...");
		modelAndView.setViewName("users/mysetting");

		return modelAndView;

	}

	@RequestMapping("/settings")
	public String settings(Principal principal, Model model, HttpSession session) throws Exception {
		Customer currentCustomer = repo.findByEmail(principal.getName());
		if (currentCustomer != null) {
			model.addAttribute("user_details", currentCustomer);

			return "users/mysetting";
		} else {
			session.invalidate();
			return "redirect:/";
		}

	}

	@RequestMapping("/myaffiliate")
	public String myAffiliate(Model model, Principal principal, HttpSession session) throws Exception {
		Customer currentCustomer = this.repo.findByEmail(principal.getName());
		if (currentCustomer != null) {
			List<MyAffiliate> affiliatelist = userAffiliateRepository.findAllByAffiliateid(currentCustomer.getAffiliateId());
			if (affiliatelist!=null || affiliatelist.isEmpty() == false) {
				model.addAttribute("affiliatelist", affiliatelist);
				return "users/my-affiliate";
			} 
				model.addAttribute("noDataMsg", "No Record found");
			
			return "users/my-affiliate";

		} else {

			session.invalidate();
			return "redirect:/";
		}

	}

//total sale by affiliate
	@RequestMapping("/payment")
	public String totalSale(Model model, Principal principal, HttpSession session) throws Exception {

		if (principal.getName() != null) {
			Customer currentUser = this.repo.findByEmail(principal.getName());
			List<MyAffiliate> affiliatelist = userAffiliateRepository.findAllByAffiliateid(currentUser.getAffiliateId());
			if (affiliatelist.isEmpty() == false) {
				model.addAttribute("affiliatelist", affiliatelist);
			} else {
				model.addAttribute("noDataMsg", "No Record found");
			}
			return "users/payment";
		} else {
			session.invalidate();
			return "redirect:/";
		}
	}

	@GetMapping("/notification")
	public String notification(Model model, Principal principal) {
		return "users/notification";
	}

	@GetMapping("/term-condition")
	public String termCondition(Model model, Principal principal) {
		return "users/term-condition";
	}

	@GetMapping("/contact-us")
	public String contactUs(Model model, Principal principal) {
		return "users/contact-us";
	}

	@GetMapping("/privacy-policy")
	public String privacyPolicy(Model model, Principal principal) {
		return "users/privacy-policy";
	}

	// link copy section
	@PostMapping("/copy")
	public String copyLink(@RequestParam("id") Long productId, @RequestParam("vid") int vid, Principal principal) {
		if (principal.getName() != null) {
			Customer currentcustomer = repo.findByEmail(principal.getName());
			// Long pid=Long.parseLong(productId);
			Product productdetails = productRepository.findByProductId(productId);
			String generatedLink = productdetails.getLink() + "/ref_" + currentcustomer.getAffiliateId();
			System.out.println(generatedLink);	
			if (userAffiliateRepository.findByGeneratedLinks(generatedLink) == null) {
				MyAffiliate myaffiliate = new MyAffiliate();
				myaffiliate.setAffiliateid(currentcustomer.getAffiliateId());
				myaffiliate.setProductName(productdetails.getProductname());
				myaffiliate.setProductLinks(productdetails.getLink());
				myaffiliate.setGeneratedLinks(generatedLink);
				myaffiliate.setPriceOfProduct(productdetails.getPrice());
				String date = new Date().toString();
				myaffiliate.setCreatedDate(date);
				myaffiliate.setProductId(productdetails.getProductId());
				myaffiliate.setVenderid(vid);
				
				userAffiliateRepository.save(myaffiliate);
			}
		}
		return "redirect:/image/imageDetails";
	}

	@PostMapping("/socialshare")
	public String sendbyWhatsapp(@RequestParam("id") Long productId,@RequestParam("vid") int vid,Principal principal) {
		if (principal.getName() != null) {
			Customer currentcustomer = repo.findByEmail(principal.getName());
			// Long pid=Long.parseLong(productId);
			Product productdetails = productRepository.findByProductId(productId);
			String generatedLink = productdetails.getLink() + "/ref_" + currentcustomer.getAffiliateId();
			if (userAffiliateRepository.findByGeneratedLinks(generatedLink) == null) {
				MyAffiliate myaffiliate = new MyAffiliate();
				myaffiliate.setAffiliateid(currentcustomer.getAffiliateId());
				myaffiliate.setProductName(productdetails.getProductname());
				myaffiliate.setProductLinks(productdetails.getLink());
				myaffiliate.setGeneratedLinks(generatedLink);
				myaffiliate.setPriceOfProduct(productdetails.getPrice());
				String date = new Date().toString();
				myaffiliate.setCreatedDate(date);
				myaffiliate.setProductId(productdetails.getProductId());
				myaffiliate.setVenderid(vid);
				userAffiliateRepository.save(myaffiliate);
			}
		}
		return "redirect:/image/imageDetails";
	}

	@RequestMapping(value = { "/logout" }, method = RequestMethod.GET)
	public String logoutDo(Model model, HttpServletRequest request, Principal principal) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		for (Cookie cookie : request.getCookies()) {
			cookie.setMaxAge(0);
		}
		System.out.println("you are successfully logedout.");

		session.invalidate();
		return "redirect:/";
	}

}
