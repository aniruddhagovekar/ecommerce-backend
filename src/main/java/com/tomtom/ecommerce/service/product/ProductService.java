package com.tomtom.ecommerce.service.product;

import java.util.List;

import com.tomtom.ecommerce.database.model.Product;

public interface ProductService {
	Product save(Product product);
	
	Product update(Product product);
	
	void delete(Product product);
	
	Iterable<Product> getAllProducts();
	
	Product getProductById(long productId);
	
	Iterable<Product> getProductsForGivenIds(List<Long> productIds);
}
