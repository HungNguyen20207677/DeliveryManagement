package com.sapo.edu.backend.service;

import com.sapo.edu.backend.dto.ShopsDTO;
import com.sapo.edu.backend.model.Shops;
import com.sapo.edu.backend.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopsService {
    // Get a list of shops
    ResponseEntity<List<Shops>> getAllShops();

    // Get a shop by id
    ResponseEntity<Shops> getShopById(int id);

    // Add a shop
    ResponseEntity<Shops> createShop(ShopsDTO shopsDTO);

    // Edit a shop
    ResponseEntity<Shops> updateShop(int id,  ShopsDTO shopsDTO);

    // Delete a shop
    ResponseEntity<HttpStatus> deleteShop(int id);

    // Get a list of shops with pagination
    ResponseEntity<Page<Shops>> getShopsListPagination(int currentPage, int pageSize);
}
