package com.sapo.edu.backend.repository;

import com.sapo.edu.backend.model.Shops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopsRepository extends JpaRepository<Shops,Integer> {
}
