package com.beboustore.demo.repositories;

import com.beboustore.demo.models.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
