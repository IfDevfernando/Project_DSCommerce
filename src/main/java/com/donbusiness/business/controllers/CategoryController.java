package com.donbusiness.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.donbusiness.business.DTO.CategoryDTO;
import com.donbusiness.business.DTO.ProductMinDTO;
import com.donbusiness.business.services.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll() {
		//this function is in service
		List<CategoryDTO> list=categoryService.findAll();
		return ResponseEntity.ok(list);
	}
	
	
	
}
