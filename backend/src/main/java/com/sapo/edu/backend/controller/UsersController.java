package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.dto.UsersDTO;
import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.service.implement.UsersServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    private UsersServiceImpl usersService;


    // Get a specific user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@RequestParam @Valid int userId) {
        return usersService.getUserById(userId);
    }

    // Update a user's information
    @PutMapping("/admin/users/{id}")
    public ResponseEntity<Users> updateUser(@RequestParam @Valid int userId, @RequestBody @Valid UsersDTO usersDTO) {
        return usersService.updateUser(userId, usersDTO);
    }

    // Delete a user
    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam @Valid int userId) {
        return usersService.deleteUser(userId);
    }
}
