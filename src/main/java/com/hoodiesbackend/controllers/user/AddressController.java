package com.hoodiesbackend.controllers.user;

import com.hoodiesbackend.entities.user.address.Address;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.services.user.address.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/post")
    public ResponseEntity<Response> create(@RequestBody Address body){
        return ResponseHandler.ok(this.addressService.create(body));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Response> getAllFor(@PathVariable Long userId){
        return ResponseHandler.ok(this.addressService.getAllFor(userId));
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getAll(){
        return ResponseHandler.ok(this.addressService.getAll());
    }
}
