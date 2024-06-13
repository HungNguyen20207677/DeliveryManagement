package com.sapo.edu.backend.service;

import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.model.enumclasses.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShippersService {

    // Get a list of shippers
    ResponseEntity<List<Users>> getShippersListPagination(int currentPage, int pageSize);
}
