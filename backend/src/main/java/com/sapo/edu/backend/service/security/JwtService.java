package com.sapo.edu.backend.service.security;

import com.sapo.edu.backend.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    // 256-bit secret key to create and verify Jason Web Token (JWT)
    private final String SECRET_KEY = "7771baf73b6194109735ab9d8726930463a94608e6dd6be81e5200f33631a93d";

    // Method to check the validation of JWT
    public boolean isValid(String token, UserDetails user) {
        String username = extractUsername(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    // Method to check if JWT is expired or not
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Method to extract a specific property from the token's payload
    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    // Method to extract expiration of token from JWT payload
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Method to extract username from JWT payload
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }



    // Method to extract all the payload from the JWT by decode and verify the key
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Method to create JWT from user's information
    public String generateToken(Users user) {
        // Tạo JWT với các thông tin cần thiết
        String token = Jwts
                .builder()
                .subject(user.getUsername())                                            // The subject of the token is the username
                .issuedAt(new Date(System.currentTimeMillis()))                         // When JWT is created
                .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // Expired after 24 hours
                .signWith(getSigningKey())                                              // Sign with Signing key from the secret key
                .compact();

        return token;
    }

    // Method to get signing key from the encoded secret key
    private SecretKey getSigningKey() {
        // Decode the secret key from hex string and return signing key
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

