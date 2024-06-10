package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.service.implement.UsersServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;


    // Get a specific user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getCategoryById(@RequestParam @Valid int userId) {
        return usersService.getUserById(userId);
    }


}
