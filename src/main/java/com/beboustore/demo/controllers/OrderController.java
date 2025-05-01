package com.beboustore.demo.controllers;

import com.beboustore.demo.models.Order;
import com.beboustore.demo.repositories.OrderRepository;
import com.beboustore.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class OrderController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getOrders() {
        var listOrder = (List<Order>) orderRepository.findAll();
        return listOrder;
    }

    @PostMapping("/create-order")
    public ResponseEntity<String> createOrderAndSaveCustomer(@RequestBody Order order) {
        orderService.createOrderAndSaveCustomer(order);
        return ResponseEntity.ok("commande ajoutée");
    }
}