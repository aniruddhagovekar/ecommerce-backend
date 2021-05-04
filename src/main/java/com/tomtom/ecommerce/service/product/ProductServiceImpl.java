package com.tomtom.ecommerce.service.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomtom.ecommerce.database.model.Product;
import com.tomtom.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		return save(product);
	}

	@Override
	public void delete(Product product) {
		product.setStatus(0);
		save(product);
	}

	@Override
	public Iterable<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long productId) {
		Optional<Product> product = productRepository.findById(productId);
		if(product.isPresent()) {
			return product.get();
		}
		return null;
	}

	@Override
	public Iterable<Product> getProductsForGivenIds(List<Long> productIds) {
		return productRepository.findAllById(productIds);
	}

}
