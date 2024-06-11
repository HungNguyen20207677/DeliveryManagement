package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.repository.OrdersRepository;
import com.sapo.edu.backend.repository.UsersRepository;
import com.sapo.edu.backend.service.OrdersService;
import com.sapo.edu.backend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders-management")
public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/orders")
    public List<Orders> getAllCategories() {
        // Display in web page
        return ordersRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public List<Orders> getOrderById(@PathVariable("id") int id) {
        return ordersRepository.getOrdersById(id);
    }

    @PostMapping("/orders")
    public ResponseEntity<Orders> addOrder(@RequestBody Orders order) {
        // Get the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Retrieve the username
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Find the user by username
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the user in the order
        order.setUser(user);
        
        // Save the order
        Orders newOrder = ordersRepository.save(order);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newOrder);
    }


    @PutMapping("/orders/{id}")
    public ResponseEntity<Orders> updateOrder(@PathVariable("id") int id, @RequestBody Orders orderDetails) {
        // Retrieve the order by ID
        Optional<Orders> optionalOrder = ordersRepository.findById(id);
        if (!optionalOrder.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Orders order = optionalOrder.get();

        // Get the authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Retrieve the username
        String username = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            username = userDetails.getUsername();
        }

        if (username == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Find the user by username
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Ensure the user updating the order is the same as the user who created it
        if (!order.getUser().equals(user)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        // Update the order details
        order.setCustomerName(orderDetails.getCustomerName());
        order.setCustomerPhone(orderDetails.getCustomerPhone());
        order.setAddress(orderDetails.getAddress());
        order.setProductsList(orderDetails.getProductsList());
        order.setWeight(orderDetails.getWeight());
        order.setCOD(orderDetails.getCOD());
        order.setShippingCost(orderDetails.getShippingCost());
        order.setShipCostPaidBy(orderDetails.getShipCostPaidBy());
        order.setStatus(orderDetails.getStatus());
        order.setLastCheckedAt(orderDetails.getLastCheckedAt());
        // Update other fields as necessary

        // Save the updated order
        Orders updatedOrder = ordersRepository.save(order);

        return ResponseEntity.ok(updatedOrder);
    }
}
