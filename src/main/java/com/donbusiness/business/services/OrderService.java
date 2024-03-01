package com.donbusiness.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donbusiness.business.DTO.OrderDTO;
import com.donbusiness.business.entities.Order;
import com.donbusiness.business.repositories.OrderRepository;
import com.donbusiness.business.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		
		Order order=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
		return new OrderDTO(order);
		
		
	}

}
