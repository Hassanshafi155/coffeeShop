package com.example.coffeeshop.service;

import com.example.coffeeshop.model.AddOrderRequest;
import com.example.coffeeshop.model.AddOrderResponse;
import com.example.coffeeshop.model.OrderResponse;

public interface OrderService {

	public OrderResponse getOrder(String orderId) throws Exception;

	public AddOrderResponse addOrder(AddOrderRequest request) throws Exception;

	public OrderResponse deleteOrder(String orderId) throws Exception;
}
