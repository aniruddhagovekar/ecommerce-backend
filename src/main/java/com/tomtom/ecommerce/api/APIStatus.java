package com.tomtom.ecommerce.api;

public enum APIStatus {
	
	ERR_INVALID_DATA(100, "Input data is not valid."),
	OK(200, null),
    ERR_INTERNAL_SERVER(500, "Internal Error"),
    SQL_ERROR(501, "SQL Error"),
    ERR_BAD_REQUEST(400, "Bad request"),    
    ERR_SESSION_DATA_INVALID(603, "Session data invalid"),
    ERR_SESSION_NOT_FOUND(604, "Session not found"),
    
    //Product status
    CREATE_PRODUCT_ERROR(700, "Create product error"),
    DELETE_PRODUCT_ERROR(701, "Delete product error"),
    GET_PRODUCT_ERROR(702, "Can't get product detail"),
    UPDATE_PRODUCT_ERROR(703, "Update product error"),
    GET_LIST_PRODUCT_ERROR(704, "Get list product error"),
    
    // order 
    ERR_GET_LIST_ORDERS(800, "Can not get list orders"),
    ERR_GET_DETAIL_ORDERS(801, "Can not get detail orders"),
    ERR_ORDER_ID_EMPTY(802, "Order can't empty"),
    ERR_DELETE_ORDER(803, "Delete order is fail"),
    ERR_ORDER_ID_NOT_FOUND(804, "Order id not found"),
	ERR_CREATE_ORDER(805, "Order creation failed"),
	ERR_UPDATE_ORDER(806, "Order updation failed");
	
	private final int code;
    private final String description;

    private APIStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
