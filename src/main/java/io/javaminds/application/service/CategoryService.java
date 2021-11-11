package io.javaminds.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.javaminds.application.model.Category;
import io.javaminds.application.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	public void saveByCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public void deleteCategoryById(Integer id) {
		categoryRepository.deleteById(id);
	}

	public Optional<Category> findCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}

}
