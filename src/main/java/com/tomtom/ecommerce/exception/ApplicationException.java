package com.tomtom.ecommerce.exception;

import com.tomtom.ecommerce.api.APIStatus;

public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private APIStatus apiStatus;

    public ApplicationException(APIStatus apiStatus) {
        this.apiStatus = apiStatus;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public APIStatus getApiStatus() {
        return apiStatus;
    }
}
