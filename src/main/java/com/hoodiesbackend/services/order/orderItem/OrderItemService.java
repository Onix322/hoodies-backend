package com.hoodiesbackend.services.order.orderItem;

import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.exceptions.BadRequestException;
import com.hoodiesbackend.repositories.order.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public OrderItem create(OrderItem cartItem) {
        return orderItemRepository.save(cartItem);
    }

    public List<OrderItem> findByOrderId(Long orderId) {
        if(orderId < 1) throw new BadRequestException("Order id should be > 0");
        return orderItemRepository.findAllByOrderId(orderId);
    }
}
