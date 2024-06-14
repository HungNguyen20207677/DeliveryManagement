package com.sapo.edu.backend.repository;

import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.model.enumclasses.Roles;
import org.apache.catalina.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    // Searching the user by username
    Optional<Users> findByUsername(String username);

    // Get a list of users
    List<Users> findAllByRole(Roles role);

    // Get list of users by role with pagination
    List<Users> findAllByRole(Roles role, Pageable pageable);

}
