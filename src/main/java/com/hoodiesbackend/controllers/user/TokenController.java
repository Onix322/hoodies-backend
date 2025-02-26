package com.hoodiesbackend.controllers.user;

import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.services.user.token.TokenService;
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

    @PostMapping("/get-user-id")
    public ResponseEntity<Response> decode(@RequestBody String token) {
        return ResponseHandler.ok(tokenService.getUserId(token));
    }

    @PostMapping("/create")
    public ResponseEntity<Response> create(@RequestBody UserGetDto user) {
        return ResponseHandler.ok(tokenService.create(user));
    }

    @PostMapping("/valid")
    public ResponseEntity<Response> valid(@RequestBody String token) {
        return ResponseHandler.ok(tokenService.validateToken(token));
    }
}
