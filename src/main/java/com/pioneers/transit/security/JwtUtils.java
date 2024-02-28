package com.pioneers.transit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pioneers.transit.dto.JwtClaim;
import com.pioneers.transit.entity.UserCredential;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@Slf4j
public class JwtUtils {
    @Value("${app.transit.jwt-secret}")
    private String secretKey;
    @Value("${app.transit.jwt-expiration}")
    private long expirationInSecond;
    @Value("${app.transit.jwt-app-name}")
    private String appName;

    public String generateToken(UserCredential userCredential){
        try {
            List<String> roles = userCredential
                    .getRoles()
                    .stream()
                    .map(role -> role.getRole().name()).toList();
            return JWT
                    .create()
                    .withIssuer(appName)
                    .withSubject(userCredential.getId())
                    .withExpiresAt(Instant.now().plusSeconds(expirationInSecond))
                    .withClaim("roles", roles)
                    .sign(Algorithm.HMAC512(secretKey));
        }catch (JWTCreationException e){
            log.error("Error while creating jwt token :{}",e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public boolean verifyJwtToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getIssuer().equals(appName);
        }catch (JWTVerificationException e) {
            log.error("Invalid verivication JWT :{}",e.getMessage());
            return false;
        }
    }

    public JwtClaim getUserInfoByToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
            return JwtClaim.builder()
                    .userId(decodedJWT.getSubject())
                    .roles(roles)
                    .build();
        }catch (JWTVerificationException e){
            log.error("Invalid verivication JWT :{}",e.getMessage());
            return null;
        }
    }
}
