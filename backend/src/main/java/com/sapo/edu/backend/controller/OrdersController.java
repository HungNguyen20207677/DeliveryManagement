package com.sapo.edu.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.repository.OrdersRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/orders-management")

public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping("/orders")
    public List<Orders> getAllCategories() {
        // Display in web page
        return ordersRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public List<Orders> getOrderById(@PathVariable("id") int id) {
        return ordersRepository.getOrdersById(id);
    }
}
