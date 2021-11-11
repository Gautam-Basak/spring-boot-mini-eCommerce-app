package io.javaminds.application.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import io.javaminds.application.dto.ProductDTO;
import io.javaminds.application.model.Category;
import io.javaminds.application.model.Product;
import io.javaminds.application.service.CategoryService;
import io.javaminds.application.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/adminHome")
	public String adminHome() {
		return "adminHome";	
	}
	
	
	//Category Section 
	
	@GetMapping("/categories")
	public String getCategories(Model model) {
		
		List<Category> categories = categoryService.getAllCategories();
		model.addAttribute("categories", categories);
 		return "categories";	
	}
	
	
	@GetMapping("/categories/add")
	public String addCategory(Model model) {
		
		Category category = new Category();
		model.addAttribute("category", category);
		return "categoriesAdd";	
	}
	
	
	@PostMapping("/categories/save")
	public String saveCategory(@ModelAttribute("category") Category category) {
		
		categoryService.saveByCategory(category);
		return "redirect:/admin/categories";
	}
	
	
	@GetMapping("/categories/delete/{id}")
	public String deleteCategory(@PathVariable("id") Integer id) {
		
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/categories";	
	}
	
	
	@GetMapping("/categories/update/{id}")
	public String updateCategory(@PathVariable("id") Integer id, Model model) {
		
		Optional<Category> cate = categoryService.findCategoryById(id);
		if(cate.isPresent()) {
			model.addAttribute("category", cate.get());
			return "categoriesAdd";	
		}else {
			return "404";
		}
			
	}
	
	//Product Section
	
	@GetMapping("/products")
	public String getProducts(Model model) {
		
		List<Product> products = productService.getAllProducts();
		model.addAttribute("products", products);
 		return "products";	
	}
	
	
	@GetMapping("/products/add")
	public String addProducts(Model model) {
		
		List<Category> categories = categoryService.getAllCategories();
		ProductDTO productDTO = new ProductDTO();
		model.addAttribute("categories", categories);
		model.addAttribute("productDTO", productDTO);
 		return "productsAdd";	
	}
	
	@PostMapping("/products/save")
	public String saveProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
							  @RequestParam("productImage") MultipartFile file,
			                  @RequestParam("imgName") String imgName) throws IOException {
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		product.setCategory(categoryService.findCategoryById(productDTO.getCategoryId()).get());
		
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		
		productService.saveByProduct(product);
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		
		productService.deleteProductById(id);
		return "redirect:/admin/products";	
	}
	
	
	@GetMapping("/product/update/{id}")
	public String updateProduct(@PathVariable("id") int id, Model model) {
		
		Product product = productService.findProductById(id).get();
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());
		
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("productDTO", productDTO);
		return "productsAdd";
	
	}

}
