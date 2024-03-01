package com.donbusiness.business.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.donbusiness.business.DTO.OrderDTO;
import com.donbusiness.business.DTO.ProductDTO;
import com.donbusiness.business.services.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping(value="/{id}")
	public ResponseEntity<OrderDTO>  findById( @PathVariable Long id) {
		//this function is in service
		OrderDTO dto=orderService.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_CLIENT')")
	@PostMapping
	public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO dto) {
		
		dto=orderService.insert(dto);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	
}
