package com.tomtom.ecommerce.service.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.api.request.model.PaymentRequest;
import com.tomtom.ecommerce.data.model.OrderStatus;
import com.tomtom.ecommerce.data.model.PaymentStatus;
import com.tomtom.ecommerce.database.model.Order;
import com.tomtom.ecommerce.database.model.Payment;
import com.tomtom.ecommerce.repository.OrderRepository;
import com.tomtom.ecommerce.service.payment.PaymentService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentService paymentService;
	
	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order update(Order order) {
		return save(order);
	}

	@Override
	public Order getOrderByOrderId(long orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isPresent()) {
			return order.get();
		}
		return null;
	}

	@Override
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public boolean cancelOrder(long orderId) {
		Order order = getOrderByOrderId(orderId);
		if(order != null) {
			order.setStatus(OrderStatus.CANCELLED);
			update(order);
			paymentService.refundPayment(order);
			return true;
		}
		return false;
	}

	@Override
	public double getGrandTotal(long orderId) {
		Order order = getOrderByOrderId(orderId);
		if(order != null) {
			return order.getItems().stream().map(item -> item.getTotalPrice()).reduce(0.0, (a,b) -> a+b);
		}
		return 0;
	}

	@Override
	public Order updateStatus(long orderId, OrderStatus status) {
		Order order = getOrderByOrderId(orderId);
		if(order != null) {
			order.setStatus(status);
			return save(order);
		}
		return null;
	}

	@Override
	public void delete(long orderId) {
		orderRepository.deleteById(orderId);
	}

	@Override
	public Order placeOrder(long orderId, PaymentRequest paymentRequest) {
		String transactionId = paymentService.makePayment(paymentRequest);
		Order order = getOrderByOrderId(orderId);
		Payment payment = new Payment();
		payment.setTransactionId(transactionId);
		payment.setStatus(PaymentStatus.SUCCESS);
		order.setPayment(payment);
		return save(order);
	}

}
