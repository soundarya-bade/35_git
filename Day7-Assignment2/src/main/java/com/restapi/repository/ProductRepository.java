package com.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restapi.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("select p from Product p INNER JOIN p.category c where c.categoryName=:categoryName")
	List<Product> fetchProductsByCategoryName(String categoryName);
	
	List<Product> findByCategoryCategoryName(String categoryName);
	
	@Query("select p from Product p where p.barCode=:barCode")
	Product fetchProductUsingJPQL(String barCode);
	
	Product findByBarCode(String barCode);
	
	@Query(value="select * from test.product p where p.bar_code=:barCode",nativeQuery=true)
	Product fetchProductUsingNative(String barCode);
	

}
