package com.hoodiesbackend.services.order.orderItem;

import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.repositories.order.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem create(OrderItem cartItem) {
        return orderItemRepository.save(cartItem);
    }
}
