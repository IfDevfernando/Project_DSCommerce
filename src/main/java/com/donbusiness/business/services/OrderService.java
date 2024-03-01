package com.donbusiness.business.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.donbusiness.business.DTO.OrderDTO;
import com.donbusiness.business.DTO.OrderItemDTO;
import com.donbusiness.business.entities.Order;
import com.donbusiness.business.entities.OrderItem;
import com.donbusiness.business.entities.OrderStatus;
import com.donbusiness.business.entities.Product;
import com.donbusiness.business.entities.User;
import com.donbusiness.business.repositories.OrderItemRepository;
import com.donbusiness.business.repositories.OrderRepository;
import com.donbusiness.business.repositories.ProductRepository;
import com.donbusiness.business.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		
		Order order=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
		return new OrderDTO(order);
		
		
	}

	//save order
	@Transactional
	public OrderDTO insert( OrderDTO dto) {
		
		Order order = new Order();
		
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		User user = userService.autheticated();
		order.setClient(user);
		
		for ( OrderItemDTO itemDTO : dto.getItems()) {
			
			Product product = productRepository.getReferenceById(itemDTO.getProductId());
			
			OrderItem item = new OrderItem(order, product,itemDTO.getQuantity(),product.getPrice());
			
			order.getItems().add(item);
			
		}
		repository.save(order);
		orderItemRepository.saveAll(order.getItems());
		
		return new OrderDTO(order);
	}

}
