package com.hoodiesbackend.entities.cart.helpers;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.entities.product.helpers.ProductMapper;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.entities.user.helpers.UserMapper;

import java.util.List;

public class CartMapper {

    public static CartDto toDto(Cart cart){

        UserGetDto userDto = UserMapper.toUserGetDto(cart.getUser());

        List<ProductDto> productsDto = cart.getProducts()
                .stream()
                .map(ProductMapper::toDto)
                .toList();

        return new CartDto.CartDtoBuilder(
                cart.getId(),
                userDto,
                productsDto
        ).build();
    }
}
