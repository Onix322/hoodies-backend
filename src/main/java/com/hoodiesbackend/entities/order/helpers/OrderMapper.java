package com.hoodiesbackend.entities.order.helpers;

import com.hoodiesbackend.entities.order.Order;
import com.hoodiesbackend.entities.product.helpers.ProductDto;
import com.hoodiesbackend.entities.product.helpers.ProductMapper;
import com.hoodiesbackend.entities.user.helpers.UserGetDto;
import com.hoodiesbackend.entities.user.helpers.UserMapper;

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
