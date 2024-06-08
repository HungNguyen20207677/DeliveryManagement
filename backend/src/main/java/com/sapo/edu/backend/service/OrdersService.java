package com.sapo.edu.backend.service;

import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;

    public Orders addOrder(Orders order) {
        return ordersRepository.save(order);
    }
}
