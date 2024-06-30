package com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.entities.Category;
import com.restapi.service.CategoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
		
	@Autowired
	CategoryService categoryService;
	
	@PostMapping
	public void createCategory(@RequestBody Category category) {
		log.info("User Controller:: createUser {}",category.getId());
		categoryService.createCategory(category);
		
	}
	
	@GetMapping("{categoryId}")
	public Category getCategoryById(@PathVariable Long categoryId) {
		log.info("User Controller:: getUserById {}",categoryId);
		return categoryService.findCategoryById(categoryId);
		
	}
	
	@PutMapping("{categoryId}")
	public void updateCategory(@PathVariable Long categoryId,@RequestBody Category category) {
		log.info("User Controller:: updateUser {}",category.getCategoryName());
		categoryService.updateCategory(categoryId, category);
		
	}
	
	@DeleteMapping("{categoryId}")
	public void deleteCategory(@PathVariable Long categoryId) {
		categoryService.deleteCategory(categoryId);
		
	}
}
