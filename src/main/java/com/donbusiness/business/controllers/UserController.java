package com.donbusiness.business.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.donbusiness.business.DTO.UserDTO;
import com.donbusiness.business.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService Service;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@GetMapping(value="/me")
	public ResponseEntity<UserDTO>  getMe( ) {
		//this function is in service
		UserDTO dto=Service.getMe();
		return ResponseEntity.ok(dto);
	}
	

	
}
