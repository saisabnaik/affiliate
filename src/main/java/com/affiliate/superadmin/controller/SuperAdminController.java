package com.affiliate.superadmin.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.affiliate.customer.Customer;
import com.affiliate.customer.CustomerRepository;
import com.affiliate.model.MyAffiliate;
import com.affiliate.product.Category;
import com.affiliate.product.Product;
import com.affiliate.product.repository.CategoryRepository;
import com.affiliate.product.repository.ProductRepository;
import com.affiliate.product.service.ProductService;
import com.affiliate.repository.AffiliateRepository;
import com.affiliate.superadmin.SuperAdminRepository;
import com.affiliate.superadmin.Superadmin;
import com.affiliate.vender.Vender;
import com.affiliate.vender.VenderRepository;

@Controller
@RequestMapping("/super-admin")
public class SuperAdminController {
	@Autowired
	private SuperAdminRepository superRepo;
	@Autowired
	private VenderRepository venRepo;
	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AffiliateRepository affiliateRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	// login page
	@RequestMapping("/login")
	public String login() {
		return "super-admin/login";
	}

	// dashboard page
	@GetMapping("/dashboard")
	public String dashboard(Principal principal, HttpSession session) throws Exception {
		try {
			if (principal.getName() != null) {
				Superadmin currentAdmin = superRepo.findByAdminemail(principal.getName());
				session.setAttribute("fullname",
						currentAdmin.getAdmin_firstName() + " " + currentAdmin.getAdmin_lastName());
				return "super-admin/dashboard";
			} else {
				return "redirect:/super-admin/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/super-admin/login";
		}
	}

	// vender-list
	@SuppressWarnings("null")
	@GetMapping("/vender-list")
	public String venderlist(Principal principal, HttpSession session, Model model)
			throws Exception, NullPointerException {

		if (principal.getName() != null) {
			List<Vender> venderlist = venRepo.findAll();
			if (venderlist != null || !venderlist.isEmpty()) {
				model.addAttribute("venderlist", venderlist);
				return "super-admin/venderlist";
			}
			model.addAttribute("no_vender", "no Data");
			return "super-admin/venderlist";
		}
		return "redirect:/super-admin/login";
	}

	// delete-vender by id
	@Transactional
	@PostMapping("/delete-vender")
	public String deleteVender(Principal principal, @RequestParam("id") int venderid, HttpSession session)
			throws Exception {
		if (principal.getName() != null) {
			venRepo.deleteByVenderid(venderid);
			session.setAttribute("successOk", "action done successfully...");
			return "redirect:/super-admin/vender-list";
		}
		return "redirect:/super-admin/login";
	}

	// view product-list
	@SuppressWarnings("null")
	@PostMapping("/product-list")
	public String productList(Principal principal, @RequestParam("id") int venderid, HttpSession session, Model map)
			throws Exception {
		if (principal.getName() != null) {
			List<Product> products = productService.getAllImagesById(venderid);
			if (products != null || !products.isEmpty()) {
				map.addAttribute("products", products);
				return "super-admin/productlist";
			}
			map.addAttribute("no_record", "No Record found");
			return "super-admin/productlist";
		}
		return "redirect:/super-admin/login";
	}

	// display each images by id
	@GetMapping("/myimage/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product, Model model)
			throws ServletException, IOException, NullPointerException {
		// log.info("Id :: " + id);
		product = productService.getImageById(id);
		if (product != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(product.get().getImage());
			response.getOutputStream().close();
		}
		model.addAttribute("no_image", "no image");
	}

	// product details
	@GetMapping("/image/productDetails")
	String showProductDetails(@RequestParam("id") Long id, Optional<Product> Product, Model model) {
		try {
			// log.info("Id :: " + id);
			if (id != 0) {
				Product = productService.getImageById(id);
				// System.out.println("this is details");
				// log.info("products :: " + Product);
				if (Product.isPresent()) {
					model.addAttribute("id", Product.get().getProductId());
					model.addAttribute("description", Product.get().getDescription());
					model.addAttribute("productname", Product.get().getProductname());
					model.addAttribute("quantity", Product.get().getQuantity());
					model.addAttribute("price", Product.get().getPrice());

					return "super-admin/productdetails";
				}
				return "super-admin/productdetails";
			}
			return "redirect:/super-admin/product-list";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/super-admin/dashboard";
		}
	}

	@GetMapping("/affiliate-list")
	public String affiliateList(Principal principal, Model model) throws Exception, NullPointerException {
		if (principal.getName() != null) {
			List<Customer> affiliaterlist = (List<Customer>) customerRepository.findAll();

			if (affiliaterlist != null || !affiliaterlist.isEmpty()) {
				model.addAttribute("affiliaterlist", affiliaterlist);
				return "super-admin/affiliaterlist";
			}
			model.addAttribute("no_affiliater", "no Data");
			return "super-admin/affiliaterlist";
		}
		return "redirect:/super-admin/login";
	}

	@Transactional
	@PostMapping("/delete-affiliater")
	public String deleteAffiliater(Principal principal, @RequestParam("id") Long userid, HttpSession session)
			throws Exception {
		if (principal.getName() != null) {
			customerRepository.deleteByUserid(userid);
			session.setAttribute("successOk", "action done successfully...");
			return "redirect:/super-admin/affiliate-list";
		}
		return "redirect:/super-admin/login";
	}

	@GetMapping("/vender-icon/{id}")
	@ResponseBody
	void venderIcon(@PathVariable("id") int id, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		Vender vender = venRepo.findByVenderid(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(vender.getImage());
		response.getOutputStream().close();

	}

	@GetMapping("/affiliater-icon/{id}")
	@ResponseBody
	void affiliaterIcon(@PathVariable("id") Long id, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException, NullPointerException {
		Customer customer = customerRepository.findByUserid(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(customer.getImage());
		response.getOutputStream().close();

	}

	// affiliater-product

	@SuppressWarnings("null")
	@PostMapping("/affiliater-product")
	public String affiliaterProductList(Principal principal, @RequestParam("id") String affiliateid,
			HttpSession session, Model map) throws Exception {
		if (principal.getName() != null) {
			List<MyAffiliate> affiliatelist = affiliateRepository.findAllByAffiliateid(affiliateid);
			if (affiliatelist != null || !affiliatelist.isEmpty()) {

				map.addAttribute("affiliatelist", affiliatelist);
				return "super-admin/affiliater-product-list";
			}
			System.out.println("no product......");
			map.addAttribute("no_record", "No Record found");
			return "super-admin/affiliater-product-list";
		}
		return "redirect:/super-admin/login";
	}

	@GetMapping("/manage-product")
	public String manageProduct(Principal principal, Model model) throws Exception, NullPointerException {
		if (principal != null) {
			List<Product> products = productService.getAllProduct();
			if (products != null) {
				model.addAttribute("products", products);
				return "super-admin/all_product";
			}
			model.addAttribute("no_record", "No record found.");
			return "super-admin/all_product";
		}
		return "redirect:/super-admin/login";
	}

	@GetMapping("/view-category")
	public String viewCategory(Principal principal, Model model) throws Exception, NullPointerException {
		if (principal != null) {
			List<Category> category = categoryRepository.findAll();
			if (category != null) {
				model.addAttribute("category", category);
				return "super-admin/category";
			}
			model.addAttribute("no_record", "no record found");
			return "super-admin/category";
		}
		return "redirect:/super-admin/login";
	}

	@PostMapping("/add-category")
	public String addCategory(Principal principal,
			@RequestParam(name = "category", required = true) String categoryTitle, HttpSession session)
			throws Exception, NullPointerException {
		if (principal != null) {
			if (!categoryTitle.trim().equals("")) {
				if (categoryTitle.matches("^[a-zA-Z ]*$")) {
					Category category = new Category();
					category.setCategoryTitle(categoryTitle);
					categoryRepository.save(category);
					session.setAttribute("successOk", "Record Saved successfully..");
					return "redirect:/super-admin/view-category";
				} else {
					session.setAttribute("error", "Category Title Only Excepts letters");
					return "redirect:/super-admin/view-category";
				}

			} else {
				session.setAttribute("error", "Category Title cannot be blank");
				return "redirect:/super-admin/view-category";
			}
		}
		return "redirect:/super-admin/login";
	}

	// edit-category
	@PostMapping("/edit-category")
	public String editCategory(@RequestParam(value = "categoryId", required = true) Long id,
			@RequestParam(value = "categoryTitle", required = true) String title, Principal principal,
			HttpSession session) throws Exception, NullPointerException {
		if (principal != null) {
			if (!title.trim().equals("")) {
				if (title.matches("^[a-zA-Z ]*$")) {
					Category category = categoryRepository.findByCategoryId(id);
					category.setCategoryTitle(title);
					categoryRepository.save(category);
					session.setAttribute("successOk", "Record Updated successfully..");
					return "redirect:/super-admin/view-category";
				} else {
					session.setAttribute("error", "Category Title Only Excepts letters");
					return "redirect:/super-admin/view-category";
				}
			} else {
				session.setAttribute("error", "Category Title cannot be blank");
				return "redirect:/super-admin/view-category";
			}
		}
		return "redirect:/super-admin/login";
	}

	// product-status
	@PostMapping("/product-status")
	public String productStatus(Principal principal, @RequestParam("id") Long productid,
			@RequestParam("productstatus") String status) {
		if (principal != null) {
			if (status.equals("0") || status.equals("1")) {
				Product product = productService.findProduct(productid);
				product.setStatus(status);
				productService.saveImage(product);
				return "redirect:/super-admin/manage-product";
			}

		}
		return "redirect:/super-admin/login";
	}

	// vender-status
	@PostMapping("/vender-status")
	public String venderStatus(Principal principal, @RequestParam("id") int venderid,
			@RequestParam("venderstatus") String status) {
		if (principal != null) {
			if (status.equals("0") || status.equals("1")) {
				Vender vender = venRepo.findByVenderid(venderid);
				vender.setStatus(status);
				venRepo.save(vender);
				return "redirect:/super-admin/vender-list";
			}
		}
		return "redirect:/super-admin/login";
	}

	@PostMapping("/affiliater-status")
	public String affiliaterStatus(Principal principal, @RequestParam("id") Long id,
			@RequestParam("affiliaterstatus") String status) {
		if (principal != null) {
			if (status.equals("0") || status.equals("1")) {
				Customer customer = customerRepository.findByUserid(id);
				customer.setStatus(status);
				
				customerRepository.save(customer);
				return "redirect:/super-admin/affiliate-list";
			}

		}
		return "redirect:/super-admin/login";
	}

}
