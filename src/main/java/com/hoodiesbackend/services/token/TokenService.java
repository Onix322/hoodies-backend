package com.hoodiesbackend.services.token;

import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Payload;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenService {

    SecretKey key = new SecretKeySpec("secretKeyEcommerceSoftwareDevelopmentAcademy".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    public TokenService() {
    }

    public String create(UserGetDto user) {

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 100000))
                .signWith(key)
                .compact();
    }

    public Jwt<?, ?> decode(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(token);
    }
}
