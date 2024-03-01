package com.donbusiness.business.DTO;

import java.util.ArrayList;
import java.util.List;

import com.donbusiness.business.entities.Category;
import com.donbusiness.business.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
	
	@NotNull(message = "required price")
	@Positive(message="the price must be positive")
	private Double price;
	private String imgUrl;
	
	
	@NotEmpty( message = "must have at least one category")
	private List<CategoryDTO> categories = new ArrayList<>();
	
	

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductDTO(Product entity) {
		
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImgUrl();
		for(Category cat : entity.getCategories()) {
			categories.add(new CategoryDTO(cat));
		}
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
	
	public List<CategoryDTO> getCategories() {
		return categories;
	}

	
	

}
