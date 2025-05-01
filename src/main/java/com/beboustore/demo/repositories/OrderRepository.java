package com.beboustore.demo.repositories;

import com.beboustore.demo.models.Customer;
import com.beboustore.demo.models.Order;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Integer> {

    List<Order> findByCustomer(Customer customer);

}