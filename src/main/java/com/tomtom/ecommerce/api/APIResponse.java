package com.tomtom.ecommerce.api;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class APIResponse<T extends Object> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int status;
    private String message;
    private T data;

    public APIResponse(APIStatus apiStatus, T data) {

        if (apiStatus == null) {
            throw new IllegalArgumentException("APIStatus must not be null");
        }

        this.status = apiStatus.getCode();
        this.message = apiStatus.getDescription();
        this.data = data;
    }
}
