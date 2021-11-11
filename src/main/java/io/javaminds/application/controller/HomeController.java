package io.javaminds.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.javaminds.application.global.GlobalData;
import io.javaminds.application.service.CategoryService;
import io.javaminds.application.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	
	@GetMapping({"/", "/home"})
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
		
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
		
	}
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(@PathVariable int id, Model model) {
		
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("products", productService.findAllProductsByCategoryID(id));
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
		
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable int id, Model model) {
		model.addAttribute("product", productService.findProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";
		
	}

}
