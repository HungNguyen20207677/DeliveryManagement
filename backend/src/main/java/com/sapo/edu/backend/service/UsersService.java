package com.sapo.edu.backend.service;

import com.sapo.edu.backend.model.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    // Get a specific user by id
    ResponseEntity<Users> getUserById(int id);
}
