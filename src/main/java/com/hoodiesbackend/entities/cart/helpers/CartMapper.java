package com.hoodiesbackend.entities.cart.helpers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.cart.CartItem.helpers.CartItemDto;
import com.hoodiesbackend.entities.cart.CartItem.helpers.CartItemMapper;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.entities.user.helpers.UserMapper;

import java.util.List;

public class CartMapper {
    public static CartDto toDto(Cart cart){
        UserGetDto user = UserMapper.toUserGetDto(cart.getUser());
        List<CartItemDto> cartItems = cart.getProducts().stream()
                .map(CartItemMapper::toDto)
                .toList();

        return CartDto.builder()
                .user(user)
                .id(cart.getId())
                .products(cartItems)
                .totalPrice(cart.getTotalPrice())
                .build();
    }
}
