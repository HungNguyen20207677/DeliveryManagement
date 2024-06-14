package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.model.enumclasses.Roles;
import com.sapo.edu.backend.service.implement.ShippersServiceImpl;
import com.sapo.edu.backend.service.implement.UsersServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShippersController {

    @Autowired
    private ShippersServiceImpl shippersService;

    // Get all users
    @GetMapping("/shippers-management/shippers")
    public ResponseEntity<List<Users>> getAllUsers() {
        return shippersService.getAllShippers();
    }


    // Get a list of shippers with pagination
    @GetMapping("/shippers-management/shippers/pagination")
    public ResponseEntity<List<Users>> getShippersListPagination(@RequestParam @Valid int currentPage, @RequestParam @Valid int pageSize) {
        return shippersService.getShippersListPagination(currentPage, pageSize);
    }
}
