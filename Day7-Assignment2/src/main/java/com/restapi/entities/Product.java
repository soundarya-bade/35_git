package com.restapi.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	private Integer quantity;
	private Boolean isStock;
	private String barCode;
	
	@ManyToOne
	@JoinColumn(name="cat_id",nullable = false)
	private Category category;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Review> reviews;

}
