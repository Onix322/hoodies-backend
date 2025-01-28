package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.passwordChange.PasswordChange;
import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import com.hoodiesbackend.services.user.ChangePasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/change-password")
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
