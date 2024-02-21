package com.donbusiness.business.DTO;

import com.donbusiness.business.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	private Long id;
	
	@Size(min=3,max=80,message="name must have a minimum of 3 and a maximumof 8 characters")
	@NotBlank(message="Required fiel")
	private String name;
	
	@Size(min=10,message="description must have minimun of 10 characters")
	@NotBlank(message="Required fiel")
	private String description;
	
	@Positive(message="the price must be positive")
	private Double price;
	private String imgUrl;
	
	

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDTO(Product entity) {
		super();
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	
	

}
