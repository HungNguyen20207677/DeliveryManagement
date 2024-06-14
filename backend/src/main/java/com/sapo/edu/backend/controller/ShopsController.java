package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.dto.ShopsDTO;
import com.sapo.edu.backend.model.Shops;
import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.repository.ShopsRepository;
import com.sapo.edu.backend.service.implement.ShopsServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopsController {
    @Autowired
    ShopsRepository shopsRepository;

    @Autowired
    private ShopsServiceImpl shopsService;

    // Get a list of shops
    @GetMapping("/shops-management/shops")
    public ResponseEntity<List<Shops>> getAllShops() {
        return shopsService.getAllShops();
    }

    // Get a shop by id
    @GetMapping("/shops-management/shops/{id}")
    public ResponseEntity<Shops> getShopById(@RequestParam int shopId) {
        return shopsService.getShopById(shopId);
    }

    // Add a shop
    @PostMapping("/shops-management/shops")
    public ResponseEntity<Shops> createShop(@RequestBody ShopsDTO shopsDTO) {
        return shopsService.createShop(shopsDTO);
    }

    // Update a shop
    @PutMapping("/shops-management/shops/{id}")
    public ResponseEntity<Shops> updateShop(@RequestParam int shopId, @RequestBody ShopsDTO shopsDTO) {
        return shopsService.updateShop(shopId, shopsDTO);
    }

    // Delete a shop
    @DeleteMapping("/shops-management/shops/{id}")
    public ResponseEntity<HttpStatus> deleteShop(@RequestParam int shopId) {
        return shopsService.deleteShop(shopId);
    }

    // Get a list of shops with pagination
    @GetMapping("/shops-management/shops/pagination")
    public ResponseEntity<Page<Shops>> getShopsListPagination(@RequestParam @Valid int currentPage, @RequestParam @Valid int pageSize) {
        return shopsService.getShopsListPagination(currentPage, pageSize);
    }
}
