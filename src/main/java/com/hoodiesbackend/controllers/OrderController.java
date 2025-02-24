package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.response.Response;
import com.hoodiesbackend.response.ResponseHandler;
import com.hoodiesbackend.services.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/post")
    public ResponseEntity<Response> create(@RequestBody Cart cart) {
        return ResponseHandler.ok(orderService.create(cart));
    }

    @DeleteMapping("/remove/{orderId}")
    public ResponseEntity<Response> remove(@PathVariable Long orderId) {
        return ResponseHandler.ok(orderService.delete(orderId));
    }

    @GetMapping("/get")
    public ResponseEntity<Response> get() {
        return ResponseHandler.ok(orderService.getAll());
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<Response> getForUser(@PathVariable Long userId) {
        return ResponseHandler.ok(orderService.getFor(userId));
    }
}
