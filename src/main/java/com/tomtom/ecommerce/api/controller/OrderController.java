package com.tomtom.ecommerce.api.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomtom.ecommerce.api.APIName;
import com.tomtom.ecommerce.api.APIResponse;
import com.tomtom.ecommerce.api.APIStatus;
import com.tomtom.ecommerce.api.request.model.CartItemRequest;
import com.tomtom.ecommerce.api.request.model.OrderRequest;
import com.tomtom.ecommerce.api.request.model.PaymentRequest;
import com.tomtom.ecommerce.api.util.ResponseUtil;
import com.tomtom.ecommerce.data.model.OrderStatus;
import com.tomtom.ecommerce.database.model.CartItem;
import com.tomtom.ecommerce.database.model.Order;
import com.tomtom.ecommerce.database.model.Product;
import com.tomtom.ecommerce.exception.ApplicationException;
import com.tomtom.ecommerce.service.order.OrderService;
import com.tomtom.ecommerce.service.product.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="orders")
@RestController
@RequestMapping(APIName.ORDERS)
public class OrderController {
	
	@Autowired
	private ResponseUtil responseUtil;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@ApiOperation(value = "get list of all orders", notes = "")
	@GetMapping(produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> getAllOrders(){
		return responseUtil.successResponse(orderService.getAllOrders());
	}
	
	@ApiOperation(value = "get order details by id", notes = "")
	@GetMapping(path=APIName.ORDER_BY_ID, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> getProductById(@PathVariable(value="order_id") Long orderId){
		Order order = orderService.getOrderByOrderId(orderId);
		if(order != null) {
			return responseUtil.successResponse(order);
		}
		throw new ApplicationException(APIStatus.ERR_ORDER_ID_NOT_FOUND); 
	}
	
	@ApiOperation(value="create order")
	@PostMapping(produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> createOrder(@RequestBody OrderRequest orderReq){
		try {
			Order order = new Order();
			order.setAddress(orderReq.getAdress());
			order.setCheckoutComment(orderReq.getCheckoutComment());
			order.setCreatedAt(new Date());
			order.setCustomerEmail(orderReq.getCustomerEmail());
			order.setCustomerFirstname(orderReq.getCustomerFirstname());
			order.setCustomerLastname(orderReq.getCustomerLastname());
			order.setCustomerMiddlename(orderReq.getCustomerMiddlename());
			order.setCustomerPrefix(orderReq.getCustomerPrefix());
			order.setCustomerSuffix(orderReq.getCustomerSuffix());
			order.setStatus(OrderStatus.IN_PROGRESS);
			order.setUpdatedAt(new Date());
			order.setUserId(orderReq.getUserId());
			
			Set<CartItem> items = new HashSet();
			for(CartItemRequest cartItem : orderReq.getItems()) {
				CartItem item = new CartItem();
				item.setOrder(order);
				Product product = productService.getProductById(cartItem.getProductId());
				item.setProduct(product);
				item.setQuantity(cartItem.getQuantity());
				items.add(item);
			}
			order.setItems(items);
			
			return responseUtil.successResponse(orderService.save(order));
		}catch(Exception e) {
			throw new ApplicationException(APIStatus.ERR_CREATE_ORDER);
		}
	}
	
	@ApiOperation(value="update order")
	@PutMapping(produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> updateOrder(@RequestBody OrderRequest orderReq){
		try {
			Order order = new Order();
			order.setId(orderReq.getId());
			order.setAddress(orderReq.getAdress());
			order.setCheckoutComment(orderReq.getCheckoutComment());
			order.setCustomerEmail(orderReq.getCustomerEmail());
			order.setCustomerFirstname(orderReq.getCustomerFirstname());
			order.setCustomerLastname(orderReq.getCustomerLastname());
			order.setCustomerMiddlename(orderReq.getCustomerMiddlename());
			order.setCustomerPrefix(orderReq.getCustomerPrefix());
			order.setCustomerSuffix(orderReq.getCustomerSuffix());
			order.setStatus(OrderStatus.IN_PROGRESS);
			order.setUpdatedAt(new Date());
			order.setUserId(orderReq.getUserId());
			
			Set<CartItem> items = new HashSet();
			for(CartItemRequest cartItem : orderReq.getItems()) {
				CartItem item = new CartItem();
				item.setOrder(order);
				Product product = productService.getProductById(cartItem.getProductId());
				item.setProduct(product);
				item.setQuantity(cartItem.getQuantity());
				items.add(item);
			}
			order.setItems(items);
			
			return responseUtil.successResponse(orderService.update(order));
		}catch(Exception e) {
			throw new ApplicationException(APIStatus.ERR_CREATE_ORDER);
		}
	}
	
	@ApiOperation(value="delete order")
	@DeleteMapping(produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> deleteOrder(@PathVariable(value="order_id") long orderId){
		orderService.delete(orderId);
		return responseUtil.successResponse(true);
	}
	
	@ApiOperation(value="get grand total for order")
	@GetMapping(path=APIName.ORDER_GRAND_TOTAL, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> getGrandTotal(@PathVariable(value="order_id") long orderId){
		return responseUtil.successResponse(orderService.getGrandTotal(orderId));
	}
	
	@ApiOperation(value="update status of order")
	@PutMapping(path=APIName.ORDER_UPDATE_STATUS, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> updateOrderStatus(@PathVariable(value="order_id") long orderId, OrderStatus orderStatus){
		return responseUtil.successResponse(orderService.updateStatus(orderId, orderStatus));
	}
	
	@ApiOperation(value="Cancel order")
	@PutMapping(path=APIName.ORDER_CANCEL, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> cancelOrder(@PathVariable(value="order_id") long orderId){
		return responseUtil.successResponse(orderService.cancelOrder(orderId));
	}
	
	@ApiOperation(value="Place order")
	@PostMapping(path=APIName.ORDER_PLACE, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> placeOrder(@PathVariable(value="order_id") long orderId, @RequestBody PaymentRequest paymentRequest){
		return responseUtil.successResponse(orderService.placeOrder(orderId,paymentRequest));
	}
}
