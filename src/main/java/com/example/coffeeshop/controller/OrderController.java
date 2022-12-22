package com.example.coffeeshop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.coffeeshop.model.AddOrderRequest;
import com.example.coffeeshop.model.AddOrderResponse;
import com.example.coffeeshop.model.OrderResponse;
import com.example.coffeeshop.service.OrderService;

@RestController
@RequestMapping(value = "coffeeApp/")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@GetMapping("orders/{orderId}")
	public ResponseEntity<Object> getOrder(@PathVariable String orderId) {
		try {
			OrderResponse response = orderService.getOrder(orderId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}

	}

	@PostMapping("orders")
	public ResponseEntity<Object> addOrder(@RequestBody AddOrderRequest request) {
		try {
			logger.info("ORDER received");
			AddOrderResponse response = orderService.addOrder(request);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}

	}

	@DeleteMapping("orders/{orderId}")
	public ResponseEntity<Object> deleteOrder(@PathVariable String orderId) {
		try {
			OrderResponse response = orderService.deleteOrder(orderId);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("ERROR: " + e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}

	}
}
