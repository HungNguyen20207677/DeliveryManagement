package com.sapo.edu.backend.controller;

import com.sapo.edu.backend.model.AuthenticationResponse;
import com.sapo.edu.backend.model.Users;
import com.sapo.edu.backend.service.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    // Constructor to inject the AuthenticationService dependency
    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    // Endpoint to handle user registration requests
    @PostMapping("/admin/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Users request
    ) {

        // Call the register method in AuthenticationService and return the response
        return ResponseEntity.ok(authService.register(request));
    }

    // Endpoint to handle user login requests
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Users request
    ) {
        // Call the authenticate method in AuthenticationService and return the response
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
