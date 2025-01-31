package com.hoodiesbackend.services.order;

import com.hoodiesbackend.entities.order.ChangeOrderStatusObject;
import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.order.dtos.OrderDto;
import com.hoodiesbackend.entities.order.dtos.OrderMapper;
import com.hoodiesbackend.exceptions.CartException;
import com.hoodiesbackend.exceptions.NotFoundException;
import com.hoodiesbackend.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(Order body){
        if(body.getProducts().isEmpty()){
            throw new CartException("Cart must not be empty");
        }

        return orderRepository.save(body);
    }

    public List<OrderDto> getAll(){
        return orderRepository.findAll()
                .stream()
                .map(OrderMapper::toDto)
                .toList();
    }

    public void delete(Long userId, Long orderId){
        orderRepository.deleteOrderByUserId(userId, orderId);
    }

    public OrderDto updateStatus(ChangeOrderStatusObject body){
        Order order = orderRepository.findById(body.getOrderId())
                .orElseThrow(() -> new NotFoundException("Order not found!"));

        order.setStatus(body.getStatus());
        return OrderMapper.toDto(orderRepository.save(order));
    }
}
