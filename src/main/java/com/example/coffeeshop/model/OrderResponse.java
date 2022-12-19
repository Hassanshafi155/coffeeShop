package com.example.coffeeshop.model;

import java.util.List;

public class OrderResponse {
	private List<String> orderItems;
	private String amount;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<String> orderItems) {
		this.orderItems = orderItems;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
