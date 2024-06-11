package com.sapo.edu.backend.repository;

import com.sapo.edu.backend.model.Orders;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {
    @Query("SELECT o FROM Orders o WHERE o.orderId = :id")
    public List<Orders> getOrdersById(@Param("id") int id);
    // Page<Orders> findAllByOrderNameContainingIgnoreCase(String name, Pageable pageable);
}
