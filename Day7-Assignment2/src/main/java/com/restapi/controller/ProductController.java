package com.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ResponseStatus(code=HttpStatus.CREATED)
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
	
	@GetMapping("fetchProductsByCategoryName/{categoryName}")
	public List<Product> fetchProductByCategoryName(@PathVariable String categoryName) {
		log.info("Product Controller:: fetchProductByCategoryName {}",categoryName);
		return productService.fetchProductsByCategoryName(categoryName);
		
	}
	
	@GetMapping("findByCategoryName/{categoryName}")
	public List<Product> findByCategoryName(@PathVariable String categoryName) {
		log.info("Product Controller:: findByCategoryName {}",categoryName);
		return productService.findByCategoryName(categoryName);
		
	}
	
	@GetMapping("fetchProductUsingJPQL/{barCode}")
	public Product fetchProductUsingJPQL(@PathVariable String barCode) {
		log.info("Product Controller:: fetchProductUsingJPQL {}",barCode);
		return productService.fetchProductUsingJPQL(barCode);
		
	}
	
	@GetMapping("findByBarCode/{barCode}")
	public Product findByBarCode(@PathVariable String barCode) {
		log.info("Product Controller:: findByBarCode {}",barCode);
		return productService.findByBarCode(barCode);
		
	}
	
	@GetMapping("fetchProductUsingNative/{barCode}")
	public Product fetchProductUsingNative(@PathVariable String barCode) {
		log.info("Product Controller:: fetchProductUsingNative {}",barCode);
		return productService.fetchProductUsingNative(barCode);
		
	}
}
