package com.hoodiesbackend.controllers.user;

import com.hoodiesbackend.entities.user.helpers.ActivationStatus;
import com.hoodiesbackend.entities.user.helpers.LogIn;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.entities.user.User;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.services.user.UserService;
import jakarta.transaction.Transactional;
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

    @GetMapping("/get/activated")
    public ResponseEntity<Response> getAllActivated() {
        return ResponseHandler.ok(this.userService.getAll(ActivationStatus.ACTIVATED));
    }

    @GetMapping("/get/deactivated")
    public ResponseEntity<Response> getAllDeactivated() {
        return ResponseHandler.ok(this.userService.getAll(ActivationStatus.DEACTIVATED));
    }

    @PutMapping("/put")
    public ResponseEntity<Response> update(@RequestBody UserGetDto entity) {
        return ResponseHandler.ok(userService.update(entity));
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseHandler.ok("Deleted");
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
