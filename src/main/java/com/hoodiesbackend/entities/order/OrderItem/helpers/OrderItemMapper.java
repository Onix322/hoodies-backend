package com.hoodiesbackend.entities.order.OrderItem.helpers;

import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.order.OrderItem.OrderItem;
import com.hoodiesbackend.entities.product.Product;
import com.hoodiesbackend.entities.product.helpers.ProductMapper;

public class OrderItemMapper {

    public static OrderItem toOrderItem(CartItem cartItem, Order order) {
        return OrderItem.builder()
                .id(null)
                .order(order)
                .initialPrice(cartItem.getProduct().getPrice())
                .product(cartItem.getProduct())
                .quantity(cartItem.getQuantity())
                .build();
    }

    public static OrderItemDto toDto(OrderItem orderItem){
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .initialPrice(orderItem.getInitialPrice())
                .quantity(orderItem.getQuantity())
                .product(ProductMapper.toDto(orderItem.getProduct()))
                .build();
    }
}
