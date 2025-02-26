package com.hoodiesbackend.controllers;

import com.hoodiesbackend.entities.order.helpers.ChangeStatusOrder;
import com.hoodiesbackend.entities.order.helpers.OrderDetails;
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
    public ResponseEntity<Response> create(@RequestBody OrderDetails cart) {
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

    @GetMapping("/get/{userId}/{orderId}")
    public ResponseEntity<Response> getOneForUser(@PathVariable Long userId, @PathVariable Long orderId) {
        return ResponseHandler.ok(orderService.getOneFor(userId, orderId));
    }

    @PutMapping("/status")
    public ResponseEntity<Response> changeStatus(@RequestBody ChangeStatusOrder body) {
        return ResponseHandler.ok(orderService.changeStatus(body));
    }
}
