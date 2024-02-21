package com.donbusiness.business.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.donbusiness.business.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	//search per name
	@Query("SELECT obj FROM Product obj "
			+ "WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%',:name,'%')) ")
	Page<Product> searchByName(String name,Pageable pageable);
		
	
	

}
