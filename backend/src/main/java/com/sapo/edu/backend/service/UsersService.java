package com.sapo.edu.backend.service;

import com.sapo.edu.backend.dto.UsersDTO;
import com.sapo.edu.backend.model.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    // Get a specific user by id
    ResponseEntity<Users> getUserById(int id);

    // Edit a user's information
    ResponseEntity<Users> updateUser(int id, UsersDTO usersDTO);

    // Delete a user
    ResponseEntity<HttpStatus> deleteUser(int id);
}
