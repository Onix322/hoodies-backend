package com.hoodiesbackend.entities.cart.helpers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.productCart.helpers.ProductCartDto;
import com.hoodiesbackend.entities.productCart.helpers.ProductCartMapper;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.entities.user.helpers.UserMapper;

import java.util.List;

public class CartMapper {

    public static CartDto toDto(Cart cart) {

        UserGetDto userDto = UserMapper.toUserGetDto(cart.getUser());

        List<ProductCartDto> productCartDtos = cart.getProducts()
                .stream()
                .map(ProductCartMapper::toDto)
                .toList();

        return new CartDto.CartDtoBuilder(
                cart.getId(),
                userDto,
                productCartDtos
        ).build();
    }
}
