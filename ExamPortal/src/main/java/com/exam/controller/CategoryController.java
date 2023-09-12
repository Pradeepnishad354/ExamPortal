package com.exam.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Category;
import com.exam.service.CategoryService;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	
	/// add category
	@PostMapping("/")
	public ResponseEntity<?> addCategory(@RequestBody Category category) {

		Category addCategory = this.categoryService.addCategory(category);

		return ResponseEntity.ok(addCategory);

	}
	
	// get category
	
	@GetMapping("/{categoryId}")
	public Category getCategory(@PathVariable Long categoryId ) {
		return categoryService.getCategory(categoryId);
	}
	
	
	//get All Categories 
	
	
   @GetMapping("/")
	public ResponseEntity<?> getCategories(){
		
		Set<Category> categories = categoryService.getCategories();
		
		return ResponseEntity.ok(categories);
		
		
	}
	
	
	//update category
	@PutMapping("/")
	public Category updateCategory(@RequestBody Category category) {
		
		
		return categoryService.updateCategory(category);
		
	}
	
	// delete Mapping
	
	@DeleteMapping("/{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		
		categoryService.deleteCategory(categoryId);
	}
	

}
