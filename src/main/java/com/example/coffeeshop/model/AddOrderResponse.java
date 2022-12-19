package com.example.coffeeshop.model;

public class AddOrderResponse {
	private long orderId;
	private AddOrderRequest request;
	private String expectedTime;
	private String message;

	public AddOrderResponse() {
	}

	public AddOrderResponse(long orderId, AddOrderRequest request, String expectedTime, String message) {
		super();
		this.orderId = orderId;
		this.request = request;
		this.expectedTime = expectedTime;
		this.message = message;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public AddOrderRequest getRequest() {
		return request;
	}

	public void setRequest(AddOrderRequest request) {
		this.request = request;
	}

	public String getExpectedTime() {
		return expectedTime;
	}

	public void setExpectedTime(String expectedTime) {
		this.expectedTime = expectedTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
