package com.exam.service;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.repository.CategoryRepository;

@Service
public class CategoryServiceImpl  implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public Category addCategory(Category category) {
		
		Category category2 = categoryRepository.save(category);
		return category2;
	}

	@Override
	public Category updateCategory(Category category) {
	
		return categoryRepository.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		
		return new LinkedHashSet<>(categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		
		return categoryRepository.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		
		
		Category category=new Category();
		
		category.setCid(categoryId);
		categoryRepository.deleteById(categoryId);
		
		
	}

}
