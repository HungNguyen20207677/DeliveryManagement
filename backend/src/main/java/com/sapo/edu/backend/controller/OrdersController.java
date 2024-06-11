package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.model.Orders;
import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.repository.OrdersRepository;
import com.sapo.edu.backend.repository.UsersRepository;
import com.sapo.edu.backend.specification.OrdersSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        // Note: createdAt should not be updated, so it's not included here

        // Save the updated order
        Orders updatedOrder = ordersRepository.save(order);

        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/orders/search")
    public ResponseEntity<List<Orders>> searchOrders(
            @RequestParam(required = false) String customerName,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer orderId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdAfter,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date createdBefore) {
        
        List<Specification<Orders>> specs = new ArrayList<>();
        
        if (customerName != null && !customerName.isEmpty()) {
            specs.add(OrdersSpecification.hasCustomerName(customerName));
        }
        
        if (address != null && !address.isEmpty()) {
            specs.add(OrdersSpecification.hasAddress(address));
        }
        
        if (orderId != null) {
            specs.add(OrdersSpecification.hasOrderId(orderId));
        }
        
        if (userName != null && !userName.isEmpty()) {
            specs.add(OrdersSpecification.hasUserName(userName));
        }
        
        if (createdAfter != null) {
            specs.add(OrdersSpecification.createdAfter(createdAfter));
        }
        
        if (createdBefore != null) {
            specs.add(OrdersSpecification.createdBefore(createdBefore));
        }
        
        Specification<Orders> resultSpec = specs.stream()
                .reduce((spec1, spec2) -> spec1.and(spec2))
                .orElse(null);

        List<Orders> orders;
        if (resultSpec != null) {
            orders = ordersRepository.findAll(resultSpec);
        } else {
            orders = ordersRepository.findAll();
        }

        return ResponseEntity.ok(orders);
    }
}
