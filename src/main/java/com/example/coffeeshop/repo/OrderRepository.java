package com.example.coffeeshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coffeeshop.entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

	long countByStatus(String status);
	

}
