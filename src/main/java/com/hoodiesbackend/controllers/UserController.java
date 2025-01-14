package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.LogIn;
import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import com.hoodiesbackend.entities.user.User;
import com.hoodiesbackend.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/post")
    public ResponseEntity<Response> create(@RequestBody User body) {
        System.out.println(body);
        return ResponseHandler.ok(userService.create(body));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> get(@PathVariable Long id) {
        return ResponseHandler.ok(userService.read(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LogIn body) {
        System.out.println(body);
        return ResponseHandler.ok(userService.login(body));
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getAll() {
        return ResponseHandler.fail(HttpStatus.LOCKED, "No access");
    }

    @PutMapping("/put")
    public ResponseEntity<Response> update(@RequestBody User entity) {
        return ResponseHandler.ok(userService.update(entity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return ResponseHandler.ok(userService.delete(id));
    }
}
