package com.affiliate.vender.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.affiliate.customer.Customer;
import com.affiliate.product.Category;
import com.affiliate.product.Product;
import com.affiliate.product.repository.CategoryRepository;
import com.affiliate.product.service.ProductService;
import com.affiliate.vender.Vender;
import com.affiliate.vender.VenderRepository;
import com.affiliate.vender.service.VenderService;

@Controller
@RequestMapping("/vender")
public class VenderController {

	@Autowired
	public VenderService venderService;

	@Autowired
	private VenderRepository venderRepo;

	@Autowired
	private CategoryRepository catRepository;

	@Autowired
	private ProductService productService;
	@Value("${uploadDir}")
	private String uploadFolder;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/login")
	public ModelAndView login(ModelAndView modelAndView) {
		modelAndView.setViewName("vender/login");
		return modelAndView;
	}

	@GetMapping("/home")
	public String home(Principal principal, HttpSession session) throws NumberFormatException, Exception {
		if (principal.getName() != null) {
			Vender currentVender = this.venderRepo.findByEmail(principal.getName());
			session.setAttribute("fullname", currentVender.getFirstname() + " " + currentVender.getLastname());
			return "vender/dashboard";
		} else {
			return "redirect:/vender/login";
		}

	}

	@GetMapping("/profile-details")
	public String profileDetails(Model model, Principal principal) throws Exception {
		System.out.println("profile details called");
		if (principal.getName() != null) {
			Vender currentVender = this.venderRepo.findByEmail(principal.getName());
			model.addAttribute("vender_details", currentVender);
			return "vender/profile-details";
		} else {

			return "redirect:/vender/login";
		}

	}

	@RequestMapping("/settings")
	public String settings(Principal principal, Model model, HttpSession session) throws Exception {
		System.out.println("settings");
		if (principal.getName() != null) {
			Vender currentVender = this.venderRepo.findByEmail(principal.getName());
			model.addAttribute("vender_details", currentVender);
			// session.removeAttribute("successMessage");
			return "vender/mysetting";
		} else {
			return "redirect:/vender/login";
		}

	}

	@PostMapping("/updateVender")
	public String showEditStudentPage(Vender Vender, Model model, Principal principal,
			@RequestParam("userImage") MultipartFile profileImage, HttpSession session) throws Exception {
		if (principal.getName() != null) {
			Vender currentVender = this.venderService.updateProfile(Vender, principal, profileImage);
			session.setAttribute("success", "Record updated successfully");
			return "redirect:/vender/settings";
		} else {
			return "redirect:/vender/login";
		}
	}

	@PostMapping("/changePassword")
	public String changePassword(HttpSession session, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("password") String password, Principal principal) throws Exception {
		if (principal.getName() != null) {
			Vender currentVender = this.venderService.changeMyPassword(oldPassword, password, principal);
			session.setAttribute("successMessage", "Your password hasbeen changed successfully...");
			return "redirect:/vender/settings";
		} else {

			return "redirect:/vender/login";

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
	public String termCondition(Principal principal) {
		if (principal.getName() != null) {
			return "vender/term-condition";
		} else {
			return "redirect:/vender/login";
		}
	}

	@GetMapping("/contactus")
	public String contactUs(Principal principal) {
		if (principal.getName() != null) {
			return "vender/contact-us";
		} else {
			return "redirect:/vender/login";
		}

	}

	@GetMapping("/privacyPolicy")
	public String privacyPolicy(Principal principal) {
		if (principal.getName() != null) {
			return "vender/privacy-policy";
		} else {
			return "redirect:/vender/login";
		}
	}

	// product section start

	@GetMapping("/add-product")
	public String addProduct(Model model, Principal principal) {
		if (principal.getName() != null) {
			List<Category> productCategories = catRepository.findAll();
			model.addAttribute("category", productCategories);
			return "product/add-product";
		} else {
			return "redirect:/vender/login";
		}

	}

	@PostMapping("/image/saveImageDetails")
	public String createProduct(@RequestParam("productname") String productname, @RequestParam("price") double price,
			@RequestParam("description") String description, @RequestParam("quantity") int quantity,
			@RequestParam("productlink") String productlink, Principal principal, @RequestParam("category") String category,
			Model model, HttpServletRequest request, final @RequestParam("image") MultipartFile file,
			HttpSession session) {

		try {
			Vender currentVender = venderRepo.findByEmail(principal.getName());
		
			String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
			log.info("uploadDirectory:: " + uploadDirectory);
			String fileName = file.getOriginalFilename();
			String filePath = Paths.get(uploadDirectory, fileName).toString();
			log.info("FileName: " + file.getOriginalFilename());
			if (fileName == null || fileName.contains("..")) {
				model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
				// return new ResponseEntity<>("Sorry! Filename contains invalid path sequence "
				// + fileName, HttpStatus.BAD_REQUEST);
				return "redirect:/vender/add-product";
			}
			String[] productnames = productname.split(",");
			String[] descriptions = description.split(",");
			Date createDate = new Date();
			//log.info("Name: " + productnames[0] + " " + filePath);
			//log.info("description: " + descriptions[0]);
			//log.info("price: " + price);
			try {
				File dir = new File(uploadDirectory);
				if (!dir.exists()) {
					log.info("Folder Created");
					dir.mkdirs();
				}
				// Save the file locally
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(file.getBytes());
				stream.close();
			} catch (Exception e) {
				log.info("in catch");
				e.printStackTrace();
			}
			byte[] imageData = file.getBytes();
			Product product = new Product();
			product.setProductname(productnames[0]);
			product.setQuantity(quantity);
			product.setImage(imageData);
			product.setPrice(price);
			product.setDescription(descriptions[0]);
			product.setCreateDate(createDate);
			product.setCategory(category);
			product.setVid(currentVender.getVenderId());
			product.setLink(productlink);
			productService.saveImage(product);
			model.addAttribute("successOk", "Product Saved With File" + fileName);
			return "redirect:/vender/add-product";
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception: " + e);
			// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			model.addAttribute("badrequest", HttpStatus.BAD_REQUEST);

			return "redirect:/vender/add-product";

		}

	}

	@GetMapping("/myimage/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product)
			throws ServletException, IOException {
		System.out.println("this is id");
		log.info("Id :: " + id);
		product = productService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(product.get().getImage());
		response.getOutputStream().close();
		//return "redirect:/vender/product-list";
		//return "product/productdetails";
	}

	@GetMapping("/image/imageDetails")
	String showProductDetails(@RequestParam("id") Long id, Optional<Product> Product, Model model) {
		try {
			log.info("Id :: " + id);
			if (id != 0) {
				Product = productService.getImageById(id);
				System.out.println("this is details");
				log.info("products :: " + Product);
				if (Product.isPresent()) {
					model.addAttribute("id", Product.get().getProductId());
					model.addAttribute("description", Product.get().getDescription());
					model.addAttribute("productname", Product.get().getProductname());
					model.addAttribute("quantity", Product.get().getQuantity());
					model.addAttribute("price", Product.get().getPrice());
					
					return "product/productdetails";
				}
				return "product/productdetails";
			}
			return "redirect:/vender/image/show";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/vender/home";
		}
	}

	@GetMapping("/image/show")
	public String show(Model map, Principal principal) {
		System.out.println("method called image show");

		if (principal.getName() != null) {
			Vender currentVender = venderRepo.findByEmail(principal.getName());
			List<Product> images = productService.getAllImagesById(currentVender.getVenderId());

			if (images.isEmpty() == false) {
				map.addAttribute("images", images);

			} else {
				map.addAttribute("noDataMsg", "No Record found");
			}

			return "product/productlist";
		} else {
			return "redirect:/vender/login";
		}

	}

//  product section end 	

}
