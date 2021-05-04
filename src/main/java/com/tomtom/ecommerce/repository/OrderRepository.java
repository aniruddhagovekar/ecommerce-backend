package com.tomtom.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.tomtom.ecommerce.database.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
