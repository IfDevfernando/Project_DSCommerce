package com.donbusiness.business.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.donbusiness.business.DTO.ProductDTO;
import com.donbusiness.business.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ProductDTO>  findById( @PathVariable Long id) {
		//this function is in service
		ProductDTO dto=productService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAll(@RequestParam (name="name",defaultValue = " ") String name,Pageable pageable) {
		//this function is in service
		Page<ProductDTO> dto=productService.findAll(name,pageable);
		return ResponseEntity.ok(dto);
	}
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
		
		dto=productService.insert(dto);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ProductDTO>  update(@PathVariable Long id,@Valid @RequestBody ProductDTO dto) {
		//this function is in service
		dto=productService.update(id,dto);
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void>  delete(@PathVariable Long id) {
		//this function is in service
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}