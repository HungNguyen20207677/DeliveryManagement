package com.sapo.edu.backend.service;

import com.sapo.edu.backend.dto.UsersDTO;
import com.sapo.edu.backend.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {

    // Get list of users
    ResponseEntity<List<Users>> getAllUsers();

    // Get a specific user by id
    ResponseEntity<Users> getUserById(int id);

    // Edit a user's information
    ResponseEntity<Users> updateUser(int id, UsersDTO usersDTO);

    // Delete a user
    ResponseEntity<HttpStatus> deleteUser(int id);

    // Get a list of user with pagination
    ResponseEntity<Page<Users>> getUsersListPagination(int currentPage, int pageSize);
}
