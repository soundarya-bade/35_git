package com.restapi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.entities.Product;
import com.restapi.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryService categoryService;

	public void createProduct(Product product) {
		product.setIsStock(true);
		product.setBarCode(UUID.randomUUID().toString());
		productRepository.save(product);
	}

	public Product fetchProductById(Long productId) {
		return productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product not found"));
	}

	public void updateProduct(Long productId, Product inputProduct) {
		Product dbProduct=fetchProductById(productId);
		if(null!=categoryService.findCategoryById(inputProduct.getCategory().getId())){
			log.info("Product Service:: updateProduct {} {}",dbProduct.getId(),dbProduct.getName());
			dbProduct.setName(inputProduct.getName());
			dbProduct.setPrice(inputProduct.getPrice());
			dbProduct.setQuantity(inputProduct.getQuantity());
			productRepository.save(dbProduct);
			log.info("Product updated successfully");		
		}
		else {
			throw new RuntimeException("Category not found");
		}
		
	}
	public void deleteProduct(Long productId) {

		if(productRepository.existsById(productId)) {
			log.info("Product Service:: deleteProduct {}",productId);
			productRepository.deleteById(productId);
			log.info("Product deleted successfully");
		}
		else {
			throw new RuntimeException("Product not found");
		}
	}

}
