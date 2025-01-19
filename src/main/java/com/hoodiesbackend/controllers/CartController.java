package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.addToCart.CartDataTransfer;
import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
import com.hoodiesbackend.services.cart.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/post")
    public ResponseEntity<Response> createCart(@RequestBody Cart body){
        return ResponseHandler.ok(cartService.create(body));
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getCarts(){
        return ResponseHandler.ok(cartService.getAll());
    }

    @GetMapping("/get/cart/for/{id}")
    public ResponseEntity<Response> getUserCart(@PathVariable Long id){
        return ResponseHandler.ok(cartService.readByUserId(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteCart(@PathVariable Long id){
        return ResponseHandler.ok(cartService.delete(id));
    }

    @PutMapping("/put")
    public ResponseEntity<Response> updateCart(@RequestBody Cart body){
        return ResponseHandler.ok(cartService.update(body));
    }

    @PutMapping("/add-to-cart")
    public ResponseEntity<Response> addToCart(@RequestBody CartDataTransfer body){
        return ResponseHandler.ok(cartService.addProductToCart(body.getProductId(), body.getUserId()));
    }

    @PutMapping("/remove-from-cart")
    public ResponseEntity<Response> removeFromCart(@RequestBody CartDataTransfer body){
        return ResponseHandler.ok(cartService.removeProductFromCart(body.getProductId(), body.getUserId()));
    }

    @DeleteMapping("/remove-all/{userId}")
    public ResponseEntity<Response> removeAll(@PathVariable Long userId) {
        System.out.println(userId);
        return ResponseHandler.ok(cartService.removeAll(userId));
    }
}
