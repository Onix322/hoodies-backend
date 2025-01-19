package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.response.Response;
import com.hoodiesbackend.entities.response.ResponseHandler;
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
    public ResponseEntity<Response> createOrder(@RequestBody Order body) {
        System.out.println(body);
        return ResponseHandler.ok(orderService.create(body));
    }

    @GetMapping("/get")
    public ResponseEntity<Response> getAll() {
        return ResponseHandler.ok(orderService.getAll());
    }
}
