package com.hoodiesbackend.entities.order.helpers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.user.address.Address;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDetails {
    private Cart cart;
    private Address address;
    private String comments;
    private LocalDateTime createdAt;
    private LocalDateTime finalizedAt;
}
