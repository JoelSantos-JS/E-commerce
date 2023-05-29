package com.ecommerce.ECommerce.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ecommerce.ECommerce.model.LocalUser;

import jakarta.annotation.PostConstruct;

@Service
public class JwtService {

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiryInSeconds}")
    private int expiryInSeconds;

    private Algorithm algorithm;

    private static final String USERNAME_KEY = "USERNAME";
    private static final String EMAIL_KEY = "EMAIL";

    @PostConstruct
    public void postConstruct() {
        algorithm = Algorithm.HMAC256(algorithmKey);
    }

    public String getAlgorithmJWT(LocalUser user) {
        return JWT.create().withClaim(USERNAME_KEY, user.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryInSeconds * 1000))
                .withIssuer(issuer).sign(algorithm);

    }

    public String getUserName(String token) {
        return JWT.decode(token).getClaim(USERNAME_KEY).asString();
    }

    public String generateVerificaitonJWT(LocalUser user) {
        return JWT.create().withClaim(EMAIL_KEY, user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiryInSeconds * 1000))
                .withIssuer(issuer).sign(algorithm);

    }

}
