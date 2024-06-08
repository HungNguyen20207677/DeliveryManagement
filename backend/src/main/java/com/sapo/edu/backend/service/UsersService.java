package com.sapo.edu.backend.service;

import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users findByUsername(String username) {
        Optional<Users> user = usersRepository.findByUsername(username);
        return user.orElse(null); // Return user if present, otherwise return null
    }
}
