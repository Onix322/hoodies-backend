package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.services.cart.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/post")
    public Cart createCart(@RequestBody Cart body){
        return cartService.create(body);
    }

    @GetMapping("/get")
    public List<Cart> getCarts(){
        return cartService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteCart(@PathVariable Long id){
        return cartService.delete(id);
    }

    @PutMapping("/put")
    public Cart updateCart(@RequestBody Cart body){
        return cartService.update(body);
    }

}
