package com.hoodiesbackend.services.token;

import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
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
                .expiration(new Date(System.currentTimeMillis() + 3000))
                .signWith(key)
                .compact();
    }

    public void decode(String token) {

        System.out.println(Jwts.parser()
                .verifyWith(key)
                .build()
                .parse(token));
    }
}
