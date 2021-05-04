package com.tomtom.ecommerce.api.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.tomtom.ecommerce.api.APIResponse;
import com.tomtom.ecommerce.api.APIStatus;

@Component
public class ResponseUtil {
	private APIResponse createResponse(APIStatus apiStatus, Object data) {
        return new APIResponse(apiStatus, data);
    }

    // base method
    public ResponseEntity<APIResponse> buildResponse(APIStatus apiStatus, Object data, HttpStatus httpStatus) {
        return new ResponseEntity(createResponse(apiStatus, data), httpStatus);
    }

    public ResponseEntity<APIResponse> successResponse(Object data) {
        return buildResponse(APIStatus.OK, data, HttpStatus.OK);
    }

    public ResponseEntity<APIResponse> badRequestResponse() {

        return buildResponse(APIStatus.ERR_BAD_REQUEST, null, HttpStatus.BAD_REQUEST);
    }
}
