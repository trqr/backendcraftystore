package com.beboustore.demo.repositories;

import com.beboustore.demo.models.Order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}