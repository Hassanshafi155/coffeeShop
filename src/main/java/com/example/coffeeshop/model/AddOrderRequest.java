package com.example.coffeeshop.model;

import java.util.List;

public class AddOrderRequest {

	private List<String> orderList;
	private String amount;

	public AddOrderRequest() {
	}

	public AddOrderRequest(List<String> orderList, String amount) {
		super();
		this.orderList = orderList;
		this.amount = amount;
	}

	public List<String> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<String> orderList) {
		this.orderList = orderList;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
