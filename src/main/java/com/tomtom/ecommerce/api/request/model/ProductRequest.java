package com.tomtom.ecommerce.api.request.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
	
	private Long productId;
    private String name;
    private double price;
    private String imageUrl;
    private String overview;
    private int quantity;
    private int status;
    private String description;
}
