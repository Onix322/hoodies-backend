package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
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
    public ResponseEntity<Response> create(@RequestBody Cart body){
        return ResponseHandler.ok(cartService.create(body));
    }

    @PutMapping("/add-to-cart")
    public ResponseEntity<Response> addProduct(@RequestBody CartItem cartItem){
        return ResponseHandler.ok(cartService.addProduct(cartItem));
    }

    @GetMapping("get/{userId}")
    public ResponseEntity<Response> getCart(@PathVariable Long userId){
        return ResponseHandler.ok(cartService.getCartByUserId(userId));
    }

    @GetMapping("get/by-cartid/{cartId}")
    public ResponseEntity<Response> getCartLengthByUserId(@PathVariable Long cartId){
        return ResponseHandler.ok(cartService.getCartLengthByUserId(cartId));
    }

    @GetMapping("get-length/by-userid/{userId}")
    public ResponseEntity<Response> getCartLengthByCartId(@PathVariable Long userId){
        return ResponseHandler.ok(cartService.getCartLength(userId));
    }

    @DeleteMapping("delete/item/{cartId}/{itemId}")
    public ResponseEntity<Response> deleteItem(@PathVariable Long cartId, @PathVariable Long itemId){
        return ResponseHandler.ok(cartService.deleteItem(cartId, itemId));
    }
}
