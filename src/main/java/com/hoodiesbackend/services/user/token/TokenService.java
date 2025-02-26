package com.hoodiesbackend.services.user.token;

import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.exceptions.BadRequestException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class TokenService {

    private final SecretKey key = new SecretKeySpec("secretKeyEcommerceSoftwareDevelopmentAcademy".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    public TokenService() {
    }

    public String create(UserGetDto user) {

        if(user==null || user.getId() < 0) throw new BadRequestException("User should be not null or id <= 0");

        return Jwts.builder()
                .claim("name", user.getName())
                .subject(String.valueOf(user.getId()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 900000))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> getAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token);
    }

    public Boolean validateToken(String token) {
        Jws<Claims> jws = this.getAllClaims(token);
        Claims payload = jws.getPayload();

        return payload.getExpiration().after(new Date());
    }

    public Long getUserId(String token) {
        return Long.parseLong(this.getAllClaims(token)
                .getPayload()
                .getSubject());
    }

    public String getName(String token) {
        return this.getAllClaims(token).getPayload().get("name", String.class);
    }
}
