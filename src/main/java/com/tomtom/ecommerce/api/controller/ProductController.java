package com.tomtom.ecommerce.api.controller;

import java.util.Date;
import java.util.List;

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
import com.tomtom.ecommerce.api.request.model.ProductRequest;
import com.tomtom.ecommerce.api.util.ResponseUtil;
import com.tomtom.ecommerce.database.model.Product;
import com.tomtom.ecommerce.exception.ApplicationException;
import com.tomtom.ecommerce.service.product.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="products")
@RestController
@RequestMapping(APIName.PRODUCTS)
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ResponseUtil responseUtil;
	
	@ApiOperation(value = "get list of all products", notes = "")
	@GetMapping(produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> getAllProducts(){
		return responseUtil.successResponse(productService.getAllProducts());
	}
	
	@ApiOperation(value = "get product details by id", notes = "")
	@GetMapping(path=APIName.PRODUCT_BY_ID, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> getProductById(@PathVariable(value="product_id") Long productId){
		Product product = productService.getProductById(productId);
		if(product != null) {
			return responseUtil.successResponse(product);
		}
		throw new ApplicationException(APIStatus.GET_PRODUCT_ERROR); 
	}
	
	@ApiOperation(value = "get list of products for given ids", notes = "")
	@GetMapping(path=APIName.PRODUCT_BY_IDS, produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> getProductsByIds(@RequestBody List<Long> productIds){
		Iterable<Product> products = productService.getProductsForGivenIds(productIds);
		if(products != null) {
			return responseUtil.successResponse(products);
		}
		throw new ApplicationException(APIStatus.GET_LIST_PRODUCT_ERROR); 
	}
	
	@ApiOperation(value="create product")
	@PostMapping(produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> createProduct(@RequestBody ProductRequest productReq){
		try {
			Product product = new Product();
			product.setCreatedOn(new Date());
			product.setDescription(productReq.getDescription());
			product.setImageUrl(productReq.getImageUrl());
			product.setName(productReq.getName());
			product.setOverview(productReq.getOverview());
			product.setPrice(productReq.getPrice());
			product.setQuantity(productReq.getQuantity());
			product.setStatus(productReq.getStatus());
			product.setUpdatedOn(new Date());
			
			return responseUtil.successResponse(productService.save(product));
		}catch(Exception e) {
			throw new ApplicationException(APIStatus.CREATE_PRODUCT_ERROR);
		}
	}
	
	@ApiOperation(value="update product")
	@PutMapping(produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> updateProduct(@RequestBody ProductRequest productReq){
		try {
			Product product = new Product();
			product.setProductId(productReq.getProductId());
			product.setDescription(productReq.getDescription());
			product.setImageUrl(productReq.getImageUrl());
			product.setName(productReq.getName());
			product.setOverview(productReq.getOverview());
			product.setPrice(productReq.getPrice());
			product.setQuantity(productReq.getQuantity());
			product.setStatus(productReq.getStatus());
			product.setUpdatedOn(new Date());
			
			return responseUtil.successResponse(productService.update(product));
		}catch(Exception e) {
			throw new ApplicationException(APIStatus.UPDATE_PRODUCT_ERROR);
		}
	}
	
	@ApiOperation(value="delete product")
	@DeleteMapping(produces = APIName.CHARSET)
	public ResponseEntity<APIResponse> deleteProduct(@RequestBody ProductRequest productReq){
		try {
			Product product = new Product();
			product.setProductId(productReq.getProductId());
			product.setDescription(productReq.getDescription());
			product.setImageUrl(productReq.getImageUrl());
			product.setName(productReq.getName());
			product.setOverview(productReq.getOverview());
			product.setPrice(productReq.getPrice());
			product.setQuantity(productReq.getQuantity());
			product.setStatus(productReq.getStatus());
			product.setUpdatedOn(new Date());
			productService.delete(product);
			return responseUtil.successResponse(true);
		}catch(Exception e) {
			throw new ApplicationException(APIStatus.DELETE_PRODUCT_ERROR);
		}
	}
}
