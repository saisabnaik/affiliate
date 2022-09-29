package com.affiliate.vender.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping(value = { "/", ""})
	public String myMainController(Model model, Principal principal, HttpSession session,  HttpServletResponse response, Optional<Product> product) throws Exception {
		try {
	
			model.addAttribute("dbproductImages", productService.getAllActiveImages());
			
		} catch (NullPointerException e) {
			System.err.println("principal is null");
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("category", categoryRepository.findAll());

		return "index";
	}
	
	
	
	
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Product> product)
			throws ServletException, IOException {
		System.out.println("this is id");
		product = productService.getImageById(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(product.get().getImage());
		response.getOutputStream().close();
	}

}
