package com.beboustore.demo.controllers;

import com.beboustore.demo.models.Customer;
import com.beboustore.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;


    @PostMapping("/submit-customer-data")
    public ResponseEntity<Customer> submitCustomerData(@RequestBody Customer customer) {

        Customer savedCustomer = customerRepository.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }
}
