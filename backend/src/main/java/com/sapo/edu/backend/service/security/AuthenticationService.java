package com.sapo.edu.backend.service.security;

import com.sapo.edu.backend.model.AuthenticationResponse;
import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.model.enumclasses.UserStatus;
import com.sapo.edu.backend.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsersRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Constructor to inject dependencies
    public AuthenticationService(UsersRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    // Method to register a new user
    public AuthenticationResponse register(Users request) {
        // Create a new user instance and set its properties
        Users user = new Users();
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setStatus(UserStatus.UNACTIVATED);

        // Encrypt the password before saving to the database
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Save the user to the database and retrieve the saved user
        user = repository.save(user);

        // Generate a JWT token for the user and return it in the response
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    // Method to authenticate an existing user
    public AuthenticationResponse authenticate(Users request) {
        // Authenticate the user credentials using the AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // Find the user in the database by username and throw an exception if not found
        Users user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));

        // If the user exists, generate a JWT token and return it in the response
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
