package com.hoodiesbackend.entities.order.dtos;

import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.product.dtos.productDto.ProductDto;
import com.hoodiesbackend.entities.product.dtos.productDto.ProductMapper;
import com.hoodiesbackend.entities.user.dtos.UserGetDto;
import com.hoodiesbackend.entities.user.dtos.UserMapper;

import java.util.List;

public class OrderMapper {

    public static OrderDto toDto(Order order){

        UserGetDto userDto = UserMapper.toUserGetDto(order.getUser());

        List<ProductDto> productsDto = order.getProducts()
                .stream()
                .map(ProductMapper::toDto)
                .toList();

        return new OrderDto.OrderBuilder(
                order.getId(),
                order.getStatus(),
                order.getTotalPrice(),
                userDto,
                productsDto
        ).build();
    }
}
