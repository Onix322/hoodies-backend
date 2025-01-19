package com.hoodiesbackend.services.order;

import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.exceptions.CartException;
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

    public List<Order> getAll(){
        return orderRepository.findAll();
    }
}
