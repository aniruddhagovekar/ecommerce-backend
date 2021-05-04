package com.tomtom.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.tomtom.ecommerce.database.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{

}
