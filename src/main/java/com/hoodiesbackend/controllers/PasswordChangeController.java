package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.user.helpers.PasswordChange;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.services.user.ChangePasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/change-password")
@CrossOrigin("http://localhost:4200/")
public class PasswordChangeController {

    private final ChangePasswordService changePasswordService;

    public PasswordChangeController(ChangePasswordService changePasswordService) {
        this.changePasswordService = changePasswordService;
    }

    @PutMapping
    public ResponseEntity<Response> change(@RequestBody PasswordChange body){
        return ResponseHandler.ok(changePasswordService.change(body));
    }
}
