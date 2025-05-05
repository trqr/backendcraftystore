package com.beboustore.demo.services;


import com.beboustore.demo.models.Customer;
import com.beboustore.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer){
        customer.setNumberOfOrders(1);
        return customerRepository.save(customer);
    }

    public Customer isAlreadyACustomer(Customer customer){
        return customerRepository.findByCustomerMail(customer.getCustomerMail());
    }

    public Customer OrderCustomer(Customer customer) {
        Customer existingCustomer = isAlreadyACustomer(customer);

        if (existingCustomer != null) {
            existingCustomer.setNumberOfOrders(existingCustomer.getNumberOfOrders() + 1);
            customerRepository.save(existingCustomer);
            return existingCustomer;
        } else {
            customer.setNumberOfOrders(1);
            customerRepository.save(customer);
            return customer;
        }
    }

}
