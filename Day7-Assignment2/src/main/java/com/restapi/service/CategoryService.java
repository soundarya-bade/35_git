package com.restapi.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.entities.Category;
import com.restapi.exception.ResourceNotFoundException;
import com.restapi.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public void createCategory(Category category) {
		log.info("Category Service:: createCategory {} {}",category.getId(),category.getCategoryName());
		categoryRepository.save(category);
		log.info("Category saved successfully");

	}

	public List<Category> findAllCategory() {
		log.info("Category Service:: findAllCategory");
		List<Category> categoryList= categoryRepository.findAll();
		List<Category> result=categoryList.stream().sorted(Comparator.comparing(Category::getCategoryName)).toList();
		return result;
	}

	public Category findCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
	}

	
	public void updateCategory(Long categoryId,Category inputCategory) {
		Category dbCategory=findCategoryById(categoryId);
		log.info("Category Service:: updateCategory {}",dbCategory.getId());
		dbCategory.setCategoryName(inputCategory.getCategoryName());
		categoryRepository.save(dbCategory);
		log.info("Category updated successfully");

	}

	public void deleteCategory(Long categoryId) {
		if(categoryRepository.existsById(categoryId)) {
			log.info("Category Service:: deleteCategory {}",categoryId);
			categoryRepository.deleteById(categoryId);
			log.info("Category deleted successfully");
		}
		else {
			throw new ResourceNotFoundException("Category not found");
		}

	}

}
