package com.tomtom.ecommerce.api.request.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {
	private long productId;
	private int quantity;
}
