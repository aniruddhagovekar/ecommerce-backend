package com.tomtom.ecommerce.api.request.model;

import java.util.Date;
import java.util.List;

import com.tomtom.ecommerce.data.model.OrderStatus;
import com.tomtom.ecommerce.database.model.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	
	private long id;
	private List<CartItemRequest> items;
    private String userId;
    private Address adress;
    private Long paymentId;
    private Date createdAt;
    private Date updatedAt;
    private Short isActive;
    private OrderStatus status;
    private long grandTotal;
    private String checkoutComment;
    private String customerEmail;
    private String customerPrefix;
    private String customerFirstname;
    private String customerMiddlename;
    private String customerLastname;
    private String customerSuffix;
}
