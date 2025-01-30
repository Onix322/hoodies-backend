package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.user.login.LogIn;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.entities.user.User;
import com.hoodiesbackend.entities.user.dtos.UserGetDto;
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
    public ResponseEntity<Response> update(@RequestBody UserGetDto entity) {
        return ResponseHandler.ok(userService.update(entity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        return ResponseHandler.ok(userService.delete(id));
    }

    @DeleteMapping("/deactivate/{id}")
    public ResponseEntity<Response> deactivate(@PathVariable Long id) {
        return ResponseHandler.ok(userService.deactivate(id));
    }

    @DeleteMapping("/activate/{id}")
    public ResponseEntity<Response> activate(@PathVariable Long id) {
        return ResponseHandler.ok(userService.activate(id));
    }
}
