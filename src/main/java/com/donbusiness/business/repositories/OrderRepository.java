package com.donbusiness.business.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.donbusiness.business.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


}