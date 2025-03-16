package com.beboustore.demo.repositories;

import com.beboustore.demo.models.Customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
