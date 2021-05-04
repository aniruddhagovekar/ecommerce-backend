package com.tomtom.ecommerce.api.request.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
	private String cardNumber;
	private String expiryDate;
	private String name;
	private String cvv;
	private double amount;
}
