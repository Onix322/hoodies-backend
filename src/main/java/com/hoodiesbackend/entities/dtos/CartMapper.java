package com.hoodiesbackend.entities.dtos;

import com.hoodiesbackend.entities.cart.Cart;
import com.hoodiesbackend.entities.product.dtos.productDto.ProductDto;
import com.hoodiesbackend.entities.product.dtos.productDto.ProductMapper;
import com.hoodiesbackend.entities.user.dtos.UserGetDto;
import com.hoodiesbackend.entities.user.dtos.UserMapper;

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
