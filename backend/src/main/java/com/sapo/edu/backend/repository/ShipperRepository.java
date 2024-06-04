package com.sapo.edu.backend.repository;

import com.sapo.edu.backend.model.Shippers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends JpaRepository<Shippers,Integer> {
}
