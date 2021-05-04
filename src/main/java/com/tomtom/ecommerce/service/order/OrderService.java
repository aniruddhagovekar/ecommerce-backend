package com.tomtom.ecommerce.service.order;

import com.tomtom.ecommerce.api.request.model.PaymentRequest;
import com.tomtom.ecommerce.data.model.OrderStatus;
import com.tomtom.ecommerce.database.model.Order;

public interface OrderService {
	
	Order save(Order order);
	
	Order update(Order order);
	
	void delete(long orderId);
	
	Order getOrderByOrderId(long orderId);
	
	Iterable<Order> getAllOrders();
	
	boolean cancelOrder(long orderId);
	
	Order placeOrder(long orderId, PaymentRequest paymentRequest);
	
	double getGrandTotal(long orderId);
	
	Order updateStatus(long orderId, OrderStatus status);
}
