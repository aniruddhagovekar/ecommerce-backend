package com.tomtom.ecommerce.service.payment;

import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.api.request.model.PaymentRequest;
import com.tomtom.ecommerce.database.model.Order;

@Service
public class PaymentService {
	
	public String makePayment(PaymentRequest paymentRequest) {
		String transactionId = null;
		
		//Implement payment gateway integration
		
		return transactionId;
	}
	
	public String refundPayment(Order order) {
		String transactionId = null;
		
		//Implement payment gateway integration
		
		return transactionId;
	}
}
