package com.hoodiesbackend.entities.order.helpers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemDto;
import com.hoodiesbackend.entities.order.OrderItem.helpers.OrderItemMapper;
import com.hoodiesbackend.entities.order.helpers.OrderDto;
import com.hoodiesbackend.entities.user.address.helpers.AddressMapper;
import com.hoodiesbackend.entities.user.helpers.UserMapper;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order toOrder(OrderDetails orderDetails) {

        return Order.builder()
                .id(null)
                .user(orderDetails.getCart().getUser())
                .orderItems(new ArrayList<>())
                .statusOrder(StatusOrder.CONFIRMED)
                .totalPrice(orderDetails.getCart().getTotalPrice())
                .address(orderDetails.getAddress())
                .createdAt(orderDetails.getCreatedAt())
                .comments(orderDetails.getComments())
                .build();
    }

    public static OrderDto toDto(Order order){

        List<OrderItemDto> items = order.getOrderItems().stream()
                .map(OrderItemMapper::toDto)
                .toList();

        return OrderDto.builder()
                .id(order.getId())
                .userDto(UserMapper.toUserGetDto(order.getUser()))
                .orderItems(items)
                .statusOrder(order.getStatusOrder())
                .totalPrice(order.getTotalPrice())
                .addressDto(AddressMapper.toDto(order.getAddress()))
                .comments(order.getComments())
                .createdAt(order.getCreatedAt())
                .finalizedAt(order.getFinalizedAt())
                .build();
    }
}
