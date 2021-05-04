package com.tomtom.ecommerce.api;

public class APIName {
	
	public static final String CHARSET = "application/json;charset=utf-8";
	//Product api names
	public static final String PRODUCTS = "/products";
	public static final String PRODUCT_BY_ID = "/detail/{product_id}";
	public static final String PRODUCT_BY_IDS = "/list";
	public static final String PRODUCT_CREATE = "/create";
    public static final String PRODUCT_DELETE = "/delete/{product_id}";
    public static final String PRODUCT_UPDATE = "/update";
    
    //Order api names
    public static final String ORDER_CREATE = "/create";
    public static final String ORDER_UPDATE = "/update";
    public static final String ORDER_UPDATE_STATUS = "/update/status/{order_id}";
    public static final String ORDERS = "/orders";
    public static final String ORDER_BY_ID = "/details/{order_id}";
    public static final String ORDER_CANCEL = "/cancel/{order_id}";
    public static final String ORDER_GRAND_TOTAL = "/grandTotal/{order_id}";
	public static final String ORDER_DELETE = "/delete/{order_id}";
	public static final String ORDER_PLACE = "/place/{order_id}";
}
