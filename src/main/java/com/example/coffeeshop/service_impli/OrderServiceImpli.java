package com.example.coffeeshop.service_impli;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.coffeeshop.entity.OrderItemDetail;
import com.example.coffeeshop.entity.Orders;
import com.example.coffeeshop.model.AddOrderRequest;
import com.example.coffeeshop.model.AddOrderResponse;
import com.example.coffeeshop.model.OrderResponse;
import com.example.coffeeshop.repo.OrderDetailItemRepository;
import com.example.coffeeshop.repo.OrderRepository;
import com.example.coffeeshop.service.OrderService;

@Service
public class OrderServiceImpli implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpli.class);

	@Value("${expected-time-per-order}")
	private long expectedTimePerOrder;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailItemRepository orderDetailItemRepository;

	@Override
	public OrderResponse getOrder(String orderId) throws Exception {

		logger.info("getting order having id : {}", orderId);
		Optional<Orders> order = orderRepository.findById(Long.parseLong(orderId));
		if (!order.isPresent()) {
			throw new Exception("Order not found");
		}

		logger.info("Responding Order Resoponse");
		OrderResponse response = new OrderResponse();
		response.setAmount(order.get().getAmount());
		response.setOrderItems(
				order.get().getOrderItemDetail().stream().map(OrderItemDetail::getItem).collect(Collectors.toList()));
		return response;
	}

	@Override
	public AddOrderResponse addOrder(AddOrderRequest request) throws Exception {

		logger.info("Saving new order");
		Orders order = new Orders();
		order.setAmount(request.getAmount());
		order.setOrderDate(new Date());
		order.setStatus("pending");
		orderRepository.save(order);

		logger.info("Saving order details");
		request.getOrderList().forEach(x -> {
			OrderItemDetail detail = new OrderItemDetail();
			detail.setItem(x);
			detail.setOrder(order);
			orderDetailItemRepository.save(detail);
		});
		logger.info("Responding Order Response");
		AddOrderResponse response = new AddOrderResponse();
		response.setOrderId(order.getId());
		response.setRequest(request);
		long pendingOrders = orderRepository.countByStatus("Pending");
		long expectedTime = pendingOrders * expectedTimePerOrder;
		response.setExpectedTime(String.valueOf(expectedTime + expectedTimePerOrder) + " minutes");
		response.setMessage("Your Order has been placed");
		return response;
	}

	@Override
	public OrderResponse deleteOrder(String orderId) throws Exception {

		logger.info("getting order having id : {}", orderId);
		Optional<Orders> order = orderRepository.findById(Long.parseLong(orderId));
		
		// Checking if order exist or not
		if (!order.isPresent()) {
			throw new Exception("Order not found");
		}
		
		// checking if order already cancelled or not
		if (order.get().getStatus().equalsIgnoreCase("Cancelled")) {
			throw new Exception("Order already cancelled");
		}
		
		// checking if order prepared or not
		if (order.get().getStatus().equalsIgnoreCase("Completed")) {
			throw new Exception("Your Order has prepared!");
		}

		logger.info("Marking order to cancelled");
		order.get().setStatus("Cancelled");
		order.get().setLastModificationDate(new Date());
		orderRepository.save(order.get());

		logger.info("Responding Order Response");
		OrderResponse response = new OrderResponse();
		response.setAmount(order.get().getAmount());
		response.setOrderItems(
				order.get().getOrderItemDetail().stream().map(OrderItemDetail::getItem).collect(Collectors.toList()));
		response.setMessage("Your order has been cancelled!");
		return response;
	}

}
