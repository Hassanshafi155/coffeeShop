package com.example.coffeeshop.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.coffeeshop.entity.OrderItemDetail;

public interface OrderDetailItemRepository extends JpaRepository<OrderItemDetail, Long> {

}
