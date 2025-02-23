package com.hoodiesbackend.entities.cart.CartItem.helpers;

import com.hoodiesbackend.entities.cart.CartItem.CartItem;
import com.hoodiesbackend.entities.product.helpers.ProductMapper;

public class CartItemMapper {

    public static CartItemDto toDto(CartItem cartItem){
        return CartItemDto.builder()
                .id(cartItem.getId())
                .product(ProductMapper.toDto(cartItem.getProduct()))
                .quantity(cartItem.getQuantity())
                .build();
    }
}
