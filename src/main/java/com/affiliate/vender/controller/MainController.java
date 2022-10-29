package com.affiliate.vender.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.affiliate.product.Product;
import com.affiliate.product.repository.CategoryRepository;
import com.affiliate.product.service.ProductService;

@Controller
public class MainController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryRepository categoryRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value = { "/", " " })
	public String myMainController(Model model, HttpSession session, HttpServletResponse response,
			Optional<Product> product) throws Exception {
		try {
			model.addAttribute("dbproductImages", productService.getAllActiveImages());
			model.addAttribute("category", categoryRepository.findAll());
		} catch (NullPointerException e) {
			System.err.println("principal is null");
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	

		return "index";
	}

	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product,HttpSession session)
			throws ServletException, IOException {
		product = productService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(product.get().getImage());
		response.getOutputStream().close();

	}

	@GetMapping("/image/imageDetails")
	public String viewProductDetails(@RequestParam("id") Long id, Optional<Product> product, Model model) {
		try {
			model.addAttribute("category", categoryRepository.findAll());
			//log.info("Id :: " + id);
			if (id != 0) {
				product = productService.getImageById(id);
				log.info("products :: " + product);
				if (product.isPresent()) {
					model.addAttribute("id", product.get().getProductId());
					model.addAttribute("description", product.get().getDescription());
					model.addAttribute("productname", product.get().getProductname());
					model.addAttribute("quantity", product.get().getQuantity());
					model.addAttribute("price", product.get().getPrice());
					model.addAttribute("link", product.get().getLink());
					model.addAttribute("vid", product.get().getVid());
					return "product/view-product-details";
				}
				//return "product/view-product-details";
			}
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/";
		}
	}
	
	@GetMapping("/products")
	public String viewProducts(Model model) {
		model.addAttribute("dbproductImages", productService.getAllActiveImages());
		model.addAttribute("category", categoryRepository.findAll());
		return "product/products";
	}
	
	
	@GetMapping("/social-share")
	public String socialShare() {
		return "product/social-share";
	}
	
	

}
