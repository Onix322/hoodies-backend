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
    public ResponseEntity<Response> create(@PathVariable Long orderId) {
        return ResponseHandler.ok(" WORK IN PROGRESS " + orderId);
    }
}
