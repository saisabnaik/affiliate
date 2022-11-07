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
import com.affiliate.model.MyAffiliate;
import com.affiliate.product.Category;
import com.affiliate.product.Product;
import com.affiliate.product.repository.CategoryRepository;
import com.affiliate.product.repository.ProductRepository;
import com.affiliate.product.service.ProductService;
import com.affiliate.repository.AffiliateRepository;
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
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private AffiliateRepository affiliateRepository;

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
			session.setAttribute("venderid", currentVender.getVenderid());
			session.setAttribute("image", currentVender.getImage());
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
			@RequestParam("userImage") MultipartFile profileImage, HttpSession session, HttpServletRequest request)
			throws Exception {
		if (principal.getName() != null) {
			this.venderService.updateProfile(Vender, principal, profileImage, request);
			session.setAttribute("success", "Record updated successfully");
			return "redirect:/vender/settings";
		} else {
			return "redirect:/vender/login";
		}
	}

	@GetMapping("/vender-icon/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") int id, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		Vender vender = venderRepo.findByVenderid(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(vender.getImage());
		response.getOutputStream().close();

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

	@SuppressWarnings("unused")
	@GetMapping("/manage-business")
	public String manageBusiness(Model model, Principal principal) throws Exception {
		Vender currentVender = this.venderRepo.findByEmail(principal.getName());
		if (principal.getName() != null) {
			model.addAttribute("vender_details", currentVender);
			// List<Customer> customers = this.venderService.getAllCustomer();
			List<MyAffiliate> customers = affiliateRepository.findAllByVenderid(currentVender.getVenderid());
			if (customers.isEmpty() == false || customers != null) {
				model.addAttribute("customers", customers);
				return "vender/manage-business";
			}
			model.addAttribute("noDataMsg", "No Record found");
			return "vender/manage-business";
		} else {
			return "redirect:/vender/login";
		}
	}

	@GetMapping("/notification")
	public String notification(Principal principal) {
		if (principal.getName() != null) {
			return "vender/notification";
		} else {
			return "redirect:/vender/login";
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
	public String createProduct(@RequestParam(value = "productname", required = true) String productname,
			@RequestParam(value = "price", required = true) double price,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "quantity", required = true) int quantity,
			@RequestParam(value = "link", required = true) String link, Principal principal,
			@RequestParam(value = "category", required = true) String category, Model model, HttpServletRequest request,
			final @RequestParam(value = "image", required = true) MultipartFile file, HttpSession session) {
		if (principal.getName() != null) {

			try {

				Vender currentVender = venderRepo.findByEmail(principal.getName());

				String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
				// log.info("uploadDirectory:: " + uploadDirectory);
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadDirectory, fileName).toString();
				// log.info("FileName: " + file.getOriginalFilename());
				if (fileName == null || fileName.contains("..")) {
					model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
					// return new ResponseEntity<>("Sorry! Filename contains invalid path sequence "
					// + fileName, HttpStatus.BAD_REQUEST);
					return "redirect:/vender/add-product";
				}
				try {
					File dir = new File(uploadDirectory);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
					stream.write(file.getBytes());
					stream.close();
				} catch (Exception e) {
					log.info("in catch");
					e.printStackTrace();
				}
				byte[] imageData = file.getBytes();
				Product product = new Product();

				// String[] productnames = productname.split(",");
				// String[] descriptions = description.split(",");

				// product.setProductname(productnames[0]);
				// product.setDescription(descriptions[0]);
				Date createDate = new Date();
				product.setProductname(productname);
				product.setDescription(description);
				product.setQuantity(quantity);
				product.setPrice(price);
				product.setCreateDate(createDate);
				product.setCategory(category);
				product.setVid(currentVender.getVenderid());
				product.setImage(imageData);
				product.setLink(link);
				productService.saveImage(product);
				session.setAttribute("successOk", "Product Saved successfully");
				return "redirect:/vender/add-product";
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception: " + e);
				// return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				session.setAttribute("badrequest", HttpStatus.BAD_REQUEST);
				return "redirect:/vender/add-product";

			}
		} else {
			return "redirect:/vender/login";
		}

	}

	@GetMapping("/myimage/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product)
			throws ServletException, IOException {
		// log.info("Id :: " + id);
		product = productService.getImageById(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(product.get().getImage());
		response.getOutputStream().close();
		// return "redirect:/vender/product-list";
		// return "product/productdetails";
	}

	@GetMapping("/image/imageDetails")
	String showProductDetails(@RequestParam("id") Long id, Optional<Product> product, Model model) {
		try {
			// log.info("Id :: " + id);
			if (id != 0) {
				product = productService.getImageById(id);
				// System.out.println("this is details");
				// log.info("products :: " + Product);
				if (product.isPresent()) {
					model.addAttribute("id", product.get().getProductId());
					model.addAttribute("description", product.get().getDescription());
					model.addAttribute("productname", product.get().getProductname());
					model.addAttribute("quantity", product.get().getQuantity());
					model.addAttribute("price", product.get().getPrice());
					model.addAttribute("vid", product.get().getVid());
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
		if (principal.getName() != null) {
			Vender currentVender = venderRepo.findByEmail(principal.getName());
			List<Product> images = productService.getAllImagesById(currentVender.getVenderid());

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

	// delete item
	@GetMapping("/delete-product/{id}")
	public String removeItem(@PathVariable("id") Long id, Principal principal) {
		if (principal.getName() != null) {
			productService.deleteProduct(id);
			System.out.println("product successfully deleted");
		}
		return "redirect:/vender/image/show";

	}

	// find-item for edit page
	@GetMapping("/find-product/{id}")
	public String findProduct(@PathVariable("id") Long id, Principal principal, Model model) {
		System.out.println("edit product method called...");
		Product product = productService.findProduct(id);
		List<Category> productCategories = catRepository.findAll();
		model.addAttribute("category", productCategories);
		model.addAttribute("product", product);
		return "product/edit-product";
	}

	// update item from edit page
	@PostMapping("/image/update-item")
	public String updateProduct(@RequestParam(value = "productname", required = true) String productname,
			@RequestParam(value = "price", required = true) double price,
			@RequestParam(value = "description", required = true) String description,
			@RequestParam(value = "quantity", required = true) int quantity,
			@RequestParam(value = "link", required = true) String link, Principal principal,
			@RequestParam(value = "category", required = true) String category, Model model, HttpServletRequest request,
			@RequestParam(value = "image", required = false) MultipartFile file, HttpSession session,
			@RequestParam(value = "productId", required = true) Long productId) {
		System.out.println("update-item");

		if (principal.getName() != null) {
			try {
				Product product = productRepo.findByProductId(productId);
				if (file.getSize() > 0) {
					String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
					// log.info("uploadDirectory:: " + uploadDirectory);
					String fileName = file.getOriginalFilename();
					String filePath = Paths.get(uploadDirectory, fileName).toString();
					// log.info("FileName: " + file.getOriginalFilename());
					if (fileName == null || fileName.contains("..")) {
						model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
						// return new ResponseEntity<>("Sorry! Filename contains invalid path sequence "
						// + fileName, HttpStatus.BAD_REQUEST);
						return "redirect:/vender/add-product";
					}
					try {
						File dir = new File(uploadDirectory);
						if (!dir.exists()) {
							// log.info("Folder Created");
							dir.mkdirs();
						}
						// System.out.println("file path: "+uploadDirectory);
						// System.out.println("file name"+fileName);
						// Save the file locally
						BufferedOutputStream stream = new BufferedOutputStream(
								new FileOutputStream(new File(filePath)));
						stream.write(file.getBytes());
						stream.close();
					} catch (Exception e) {
						log.info("in catch");
						e.printStackTrace();
					}
					byte[] imageData = file.getBytes();
					product.setImage(imageData);
				}

				// String[] productnames = productname.split(",");
				// String[] descriptions = description.split(",");
				Date createDate = new Date();

				// product.setProductname(productnames[0]);
				// product.setDescription(descriptions[0]);
				product.setProductname(productname);
				product.setDescription(description);

				product.setQuantity(quantity);

				product.setPrice(price);
				product.setCreateDate(createDate);
				product.setCategory(category);
				// product.setVid(currentVender.getVenderId());
				product.setLink(link);

				product.setProductId(productId);
				productService.saveImage(product);
				System.out.println("item saved successfully..");
				session.setAttribute("successOk", "Product Saved successfully");
				return "redirect:/vender/image/show";
			} catch (Exception e) {
				e.printStackTrace();
				log.info("Exception: " + e);
				session.setAttribute("successOk", "Item not updated, please try again...");
				return "redirect:/vender/find-product" + productId;

			}
		} else {
			return "redirect:/vender/login";
		}

	}

//  product section end 	

}
