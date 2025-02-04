package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.services.token.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
@CrossOrigin("http://localhost:4200/")
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/decode")
    public ResponseEntity<Response> decode(@RequestBody String token) {
        return ResponseHandler.ok(tokenService.decode(token));
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody UserGetDto user) {
        return ResponseHandler.ok(tokenService.create(user));
    }
}
