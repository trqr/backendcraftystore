package com.beboustore.demo.services;


import com.beboustore.demo.models.Order;
import com.beboustore.demo.models.Customer;
import com.beboustore.demo.models.OrderDetails;
import com.beboustore.demo.repositories.CustomerRepository;
import com.beboustore.demo.repositories.OrderDetailsRepository;
import com.beboustore.demo.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;



    public void createOrder(Order order){
        Customer savedCustomer = customerRepository.save(order.getCustomer());
        saveOrderDetails(order.getCart(), order);
        orderRepository.save(order.setCustomer(savedCustomer));
    }

    public void saveOrderDetails(List<OrderDetails> cart, Order order){
        for (OrderDetails orderDetails : cart) {
            orderDetails.setOrder(order);
        }
    }

}
