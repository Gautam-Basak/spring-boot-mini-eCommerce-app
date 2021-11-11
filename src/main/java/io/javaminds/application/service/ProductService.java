package io.javaminds.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javaminds.application.model.Product;
import io.javaminds.application.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public void saveByProduct(Product product) {
		productRepository.save(product);
	}
	
	public void deleteProductById(int id) {
		productRepository.deleteById(id);
	}

	public Optional<Product> findProductById(int id) {
		return productRepository.findById(id);
	}
	
	public List<Product> findAllProductsByCategoryID(int id){
		return productRepository.findAllByCategory_Id(id);
	}

}
