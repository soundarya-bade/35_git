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

import com.restapi.entities.Product;
import com.restapi.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
		
	@Autowired
	ProductService productService;
	
	@PostMapping
	public void createProduct(@RequestBody Product product) {
		log.info("Product Controller:: createProduct {}",product.getId());
		productService.createProduct(product);
		
	}
	
	@GetMapping("{pId}")
	public Product fetchProductById(@PathVariable(name = "pId") long productId) {
		log.info("Product Controller:: fetchProductById {}",productId);
		return productService.fetchProductById(productId);
		
	}
	
	@PutMapping("{productId}")
	public void updateProduct(@PathVariable Long productId,@RequestBody Product product) {
		log.info("Product Controller:: updateProduct {}",product.getName());
		productService.updateProduct(productId, product);
		
	}
	
	@DeleteMapping("{productId}")
	public void deleteProduct(@PathVariable Long productId) {
		productService.deleteProduct(productId);
		
	}
}
