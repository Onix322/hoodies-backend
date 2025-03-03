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

    @PutMapping("/put")
    public ResponseEntity<Response> update(@RequestBody Address body){
        return ResponseHandler.ok(this.addressService.update(body));
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Response> getAllFor(@PathVariable Long userId){
        return ResponseHandler.ok(this.addressService.getAllFor(userId));
    }

    @GetMapping("/get-address/{addressId}")
    public ResponseEntity<Response> getAddress(@PathVariable Long addressId){
        return ResponseHandler.ok(this.addressService.getAddress(addressId));
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getAll(){
        return ResponseHandler.ok(this.addressService.getAll());
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<Response> delete(@PathVariable Long addressId){
        return ResponseHandler.ok(this.addressService.deleteAddress(addressId));
    }
}
