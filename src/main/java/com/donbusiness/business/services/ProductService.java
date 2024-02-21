package com.donbusiness.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.donbusiness.business.DTO.ProductDTO;
import com.donbusiness.business.entities.Product;
import com.donbusiness.business.repositories.ProductRepository;
import com.donbusiness.business.services.exceptions.ResourceDataBaseException;
import com.donbusiness.business.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	//search by id
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		
		
		Product product=repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Resource not found"));
		return new ProductDTO(product);
		
		
	}
	//search all
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(String name,Pageable pageable) {
		
		Page<Product> result=repository.searchByName(name, pageable);
		return result.map(x->new ProductDTO(x));
	}
	//send new product
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		Product entity=new Product();
		
		copyDtoToEntity(dto,entity);
		
		
		//save in repository
		entity=repository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	//update 
	@Transactional
	public ProductDTO update(Long id,ProductDTO dto) {
		try {
		Product entity= repository.getReferenceById(id);
		
		copyDtoToEntity(dto,entity);
		
		
		//save in repository
		entity=repository.save(entity);
		
		return new ProductDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found ");
		}
	}
	
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("id not found");
		}
		try {
		repository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new ResourceDataBaseException("Failure");
		}
		
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		
	}
	

}
